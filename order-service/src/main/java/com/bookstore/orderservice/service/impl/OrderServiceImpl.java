package com.bookstore.orderservice.service.impl;

import com.bookstore.orderservice.common.exception.RunTimeExceptionPlaceHolder;
import com.bookstore.orderservice.common.util.CommonUtilityMethods;
import com.bookstore.orderservice.dao.*;
import com.bookstore.orderservice.feign.BillingFeignClient;
import com.bookstore.orderservice.feign.PaymentFeignClient;
import com.bookstore.orderservice.message.BookStoreKafkaProducer;
import com.bookstore.orderservice.repository.OrderBillingAddressRepository;
import com.bookstore.orderservice.repository.OrderItemRepository;
import com.bookstore.orderservice.repository.OrderRepository;
import com.bookstore.orderservice.repository.OrderShippingAddressRepository;
import com.bookstore.orderservice.service.CartItemService;
import com.bookstore.orderservice.service.CartService;
import com.bookstore.orderservice.service.OrderService;
import com.bookstore.orderservice.utils.StringConstant;
import com.bookstore.orderservice.utils.feign.PaymentType;
import com.bookstore.orderservice.vo.request.CreateOrderRequest;
import com.bookstore.orderservice.vo.request.PreviewOrderRequest;
import com.bookstore.orderservice.vo.request.feign.CreatePaymentRequest;
import com.bookstore.orderservice.vo.response.order.CreateOrderResponse;
import com.bookstore.orderservice.vo.response.feign.FeignPaymentResponse;
import com.bookstore.orderservice.vo.response.feign.FeignAddressResponse;
import com.bookstore.orderservice.vo.response.order.PreviewOrderResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private OrderBillingAddressRepository orderBillingAddressRepository;
    private OrderShippingAddressRepository orderShippingAddressRepository;
    private CartService cartService;
    private CartItemService cartItemService;
    private BillingFeignClient billingFeignClient;
    private PaymentFeignClient paymentFeignClient;

    private BookStoreKafkaProducer kafkaProducer;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            OrderBillingAddressRepository orderBillingAddressRepository,
            OrderShippingAddressRepository orderShippingAddressRepository,
            CartService cartService,
            CartItemService cartItemService,
            BillingFeignClient billingFeignClient,
            BookStoreKafkaProducer kafkaProducer,
            PaymentFeignClient paymentFeignClient
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderBillingAddressRepository = orderBillingAddressRepository;
        this.orderShippingAddressRepository = orderShippingAddressRepository;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.billingFeignClient = billingFeignClient;
        this.paymentFeignClient = paymentFeignClient;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // lấy ra token của user
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        // tạo 1 order rỗng
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        // tạo 1 địa chỉ bằng null
        FeignAddressResponse billingAddress = null;
        // nếu mà cái billing address id truyền lên khác null, cái này bắt từ phía client mới đúng -> tạo 1 cái billing address
        if (createOrderRequest.getBillingAddressId() != null && !createOrderRequest.getBillingAddressId().isEmpty()) {
            billingAddress = billingFeignClient.getAddressById(createOrderRequest.getBillingAddressId());
            OrderBillingAddressDAO orderBillingAddress = new OrderBillingAddressDAO();
            BeanUtils.copyProperties(billingAddress, orderBillingAddress);
            createOrderResponse.setBillingAddress(orderBillingAddress);
        }

        FeignAddressResponse shippingAddress = null;
        if (createOrderRequest.getShippingAddressId() != null && !createOrderRequest.getShippingAddressId().isEmpty()) {
            shippingAddress = billingFeignClient.getAddressById(createOrderRequest.getShippingAddressId());
            OrderShippingAddressDAO orderShippingAddressDAO = new OrderShippingAddressDAO();
            BeanUtils.copyProperties(shippingAddress, orderShippingAddressDAO);
            createOrderResponse.setShippingAddress(orderShippingAddressDAO);
        }

        CartDAO cartDAO = cartService.getCart();
        if (cartDAO.getCartItemDAOS().isEmpty()) {
            throw new RuntimeException("Cart is Empty");
        }
        OrderDAO order = new OrderDAO();
        order.setUserId(userIdFromToken);
        // thêm cart item vào order;
        cartDAO.getCartItemDAOS()
                .forEach(cartItem -> {
                    OrderItemDAO orderItem = new OrderItemDAO();
                    orderItem.setOrder(order);
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setOrderExtendedPrice(cartItem.getExtendedPrice());
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setQuantity(cartItem.getQuantity());
                    /// set data
                    order.getOrderItemDAOS().add(orderItem); // dùng để save xuống db
                    createOrderResponse.getOrderItemDAOS().add(orderItem);// dùng để response phía client
                });
        // Giả sử thuế 10% trên tất cả sản phẩm
        double itemsPrice = createOrderResponse.getOrderItemDAOS().stream().mapToDouble(OrderItemDAO::getOrderExtendedPrice).sum(); // tính tổng tiền sản phẩm
        createOrderResponse.setItemsTotalPrice(itemsPrice); // response
        order.setTotalItemsPrice(itemsPrice);// response

        double taxPrice = (itemsPrice * 10) / 100;
        createOrderResponse.setTaxPrice(taxPrice);// response
        order.setTaxPrice(taxPrice);// response

        //Hardcode to 10
        double shippingPrice = 10d;
        createOrderResponse.setShippingPrice(shippingPrice);// response
        order.setShippingPrice(shippingPrice);// response

        double totalPrice = itemsPrice + taxPrice + shippingPrice;
        createOrderResponse.setTotalPrice(totalPrice);
        order.setTotalOrderPrice(totalPrice);

        //Do Payment
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest
                .builder()
                .amount((long) itemsPrice)
                .paymentType(createOrderRequest.getPaymentType())
                .information(createOrderRequest.getInformation())
                .orderId(cartDAO.getCartId()) // Cho cart id là duy nhất :))
                .build();
        if(createPaymentRequest.getPaymentType().equals(PaymentType.VNPAY)){
            FeignPaymentResponse feignPaymentResponse = paymentFeignClient.doPayment(createPaymentRequest);
            createOrderResponse.setPaymentUrl(feignPaymentResponse.getUrlPayment());
            if (feignPaymentResponse.getPaymentType() == null) {
                throw new RunTimeExceptionPlaceHolder(StringConstant.ERROR_TEXT);
            }
        }else{
            kafkaProducer.send(StringConstant.KAFKA_PAYMENT, createPaymentRequest);
        }

        /// save to local
        order.setPaid(false);
        order.setPaymentDate(null);
        order.setPaymentMethodType(createOrderRequest.getPaymentType().name());
        OrderDAO save = orderRepository.save(order);

        if (billingAddress != null) {
            OrderBillingAddressDAO orderBillingAddress = OrderBillingAddressDAO.builder()
                    .userId(billingAddress.getUserId())
                    .userName(billingAddress.getUserName())
                    .orderId(save.getOrderId())
                    .city(billingAddress.getCity())
                    .city(billingAddress.getCity())
                    .district(billingAddress.getDistrict())
                    .province(billingAddress.getProvince())
                    .detail(billingAddress.getDetail())
                    .phoneNumber(billingAddress.getPhoneNumber())
                    .build();
            orderBillingAddressRepository.save(orderBillingAddress);
        }

        if (shippingAddress != null) {
            OrderShippingAddressDAO orderShippingAddress = OrderShippingAddressDAO.builder()
                    .userId(shippingAddress.getUserId())
                    .userName(shippingAddress.getUserName())
                    .orderId(save.getOrderId())
                    .city(shippingAddress.getCity())
                    .city(shippingAddress.getCity())
                    .district(shippingAddress.getDistrict())
                    .province(shippingAddress.getProvince())
                    .detail(shippingAddress.getDetail())
                    .phoneNumber(shippingAddress.getPhoneNumber())
                    .build();
            orderShippingAddressRepository.save(orderShippingAddress);
        }

        createOrderResponse.setOrderId(save.getOrderId());
        createOrderResponse.setCreated_at(save.getCreatedAt());

        //set Payment info
        createOrderResponse.setPaid(false);
        createOrderResponse.setPaymentDate(null);
        //Clear cart
        cartItemService.removeAllCartItems(cartDAO.getCartId());

        return createOrderResponse;
    }

    @Override
    public PreviewOrderResponse previewOrder(PreviewOrderRequest previewOrderRequest) {
        PreviewOrderResponse previewOrderResponse = new PreviewOrderResponse();

        if (previewOrderRequest.getBillingAddressId() != null && !previewOrderRequest.getBillingAddressId().isEmpty()) {
            FeignAddressResponse billingAddress = billingFeignClient.getAddressById(previewOrderRequest.getBillingAddressId());
            previewOrderResponse.setBillingAddress(billingAddress);
        }

        if (previewOrderRequest.getShippingAddressId() != null && !previewOrderRequest.getShippingAddressId().isEmpty()) {
            FeignAddressResponse shippingAddress = billingFeignClient.getAddressById(previewOrderRequest.getShippingAddressId());
            if (previewOrderRequest.getBillingAddressId() == null) {
                previewOrderResponse.setBillingAddress(shippingAddress);
            }
            previewOrderResponse.setShippingAddress(shippingAddress);
        }

        CartDAO cart = cartService.getCart();

        cart.getCartItemDAOS()
                .forEach(cartItem -> {
                    OrderItemDAO orderItem = new OrderItemDAO();
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setOrderExtendedPrice(cartItem.getExtendedPrice());
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setOrderItemPrice(cartItem.getItemPrice());
                    orderItem.setQuantity(cartItem.getQuantity());
                    previewOrderResponse.getOrderItems().add(orderItem);
                });

        //HardCode to 10%
        double itemsPrice = previewOrderResponse.getOrderItems().stream().mapToDouble(OrderItemDAO::getOrderExtendedPrice).sum();
        previewOrderResponse.setItemsTotalPrice(itemsPrice);

        Double taxPrice = (itemsPrice * 10) / 100;
        previewOrderResponse.setTaxPrice(taxPrice);

        //Hardcode to 10
        Double shippingPrice = 10D;
        previewOrderResponse.setShippingPrice(shippingPrice);

        previewOrderResponse.setTotalPrice(itemsPrice + taxPrice + shippingPrice);

        return previewOrderResponse;
    }

    @Override
    public CreateOrderResponse getOrderById(String orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);

        OrderDAO order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            throw new RuntimeException("Order No Found");
        }

        if (!userIdFromToken.equals(order.getUserId())) {
            throw new RuntimeException("Order doesn't belong to this User! UnAuthorized!");
        }


        OrderBillingAddressDAO billingAddress = orderBillingAddressRepository.findByOrderId(orderId);
        OrderShippingAddressDAO shippingAddress = orderShippingAddressRepository.findByOrderId(orderId);

        CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                .orderId(orderId)
                .orderItemDAOS(order.getOrderItemDAOS())
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .shippingPrice(order.getShippingPrice())
                .isDelivered(order.isDelivered())
                .isPaid(order.isPaid())
                .itemsTotalPrice(order.getTotalItemsPrice())
                .paymentDate(order.getPaymentDate())
                .taxPrice(order.getTaxPrice())
                .totalPrice(order.getTotalOrderPrice())
                .build();

        return createOrderResponse;
    }

    @Override
    public List<CreateOrderResponse> getMyOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        List<OrderDAO> order = orderRepository.findByUserId(userIdFromToken);
        return getCreateOrderResponses(order);
    }

    @Override
    public List<CreateOrderResponse> getAllOrders() {
        Iterable<OrderDAO> order = orderRepository.findAll();

        return getCreateOrderResponses(order);
    }

    private List<CreateOrderResponse> getCreateOrderResponses(Iterable<OrderDAO> order) {
        List<CreateOrderResponse> createOrderResponseList = new ArrayList<>();
        order.forEach(o -> {
            String orderId = o.getOrderId();
            OrderBillingAddressDAO billingAddress = orderBillingAddressRepository.findByOrderId(orderId);
            OrderShippingAddressDAO shippingAddress = orderShippingAddressRepository.findByOrderId(orderId);

            CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                    .orderId(orderId)
                    .orderItemDAOS(o.getOrderItemDAOS())
                    .billingAddress(billingAddress)
                    .shippingAddress(shippingAddress)
                    .shippingPrice(o.getShippingPrice())
                    .isDelivered(o.isDelivered())
                    .isPaid(o.isPaid())
                    .itemsTotalPrice(o.getTotalItemsPrice())
                    .paymentDate(o.getPaymentDate())
                    .taxPrice(o.getTaxPrice())
                    .totalPrice(o.getTotalOrderPrice())
                    .paymentType(o.getPaymentMethodType())
                    .created_at(o.getCreatedAt())
                    .build();
            createOrderResponseList.add(createOrderResponse);
        });

        return createOrderResponseList;
    }
}
