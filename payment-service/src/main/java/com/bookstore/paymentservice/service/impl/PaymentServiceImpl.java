package com.bookstore.paymentservice.service.impl;

import com.bookstore.paymentservice.dao.PaymentMethodDAO;
import com.bookstore.paymentservice.repository.PaymentMethodRepository;
import com.bookstore.paymentservice.service.PaymentService;
import com.bookstore.paymentservice.utils.IpAddressUtil;
import com.bookstore.paymentservice.utils.PaymentEnums;
import com.bookstore.paymentservice.utils.PaymentStatus;
import com.bookstore.paymentservice.utils.VnPayConstant;
import com.bookstore.paymentservice.vo.request.CreatePaymentMethod;
import com.bookstore.paymentservice.vo.request.PaymentRequest;
import com.bookstore.paymentservice.vo.response.PaymentMethodResponse;
import com.bookstore.paymentservice.vo.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public PaymentResponse paymentWithVNPay(PaymentRequest paymentRequest, HttpServletRequest request) throws UnsupportedEncodingException {
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayConstant.vnp_Version);
        vnp_Params.put("vnp_Command", VnPayConstant.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VnPayConstant.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(paymentRequest.getVnpAmount()));
//        vnp_Params.put("vnp_BankCode", VnPayConstant.vnp_BankCode);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_CurrCode", VnPayConstant.vnp_CurrCode);
        vnp_Params.put("vnp_IpAddr", IpAddressUtil.getIpAddress(request));
        vnp_Params.put("vnp_Locale", VnPayConstant.vnp_Locale);
        vnp_Params.put("vnp_OrderInfo", paymentRequest.getVnpOrderInfo());
//        vnp_Params.put("vnp_OrderType",vnPayRequestPayment.getVnpOrderType());
        vnp_Params.put("vnp_ReturnUrl", VnPayConstant.vnp_ReturnUrl);
        vnp_Params.put("vnp_TxnRef", String.valueOf(paymentRequest.getVnpTxnRef()));
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldList = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldList);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        Iterator<String> itr = fieldList.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append("=");
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append("=");
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                if (itr.hasNext()) {
                    query.append("&");
                    hashData.append("&");
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = IpAddressUtil.hmacSHA512(VnPayConstant.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConstant.vnp_Url + "?" + queryUrl;
        return PaymentResponse
                .builder()
                .urlPayment(paymentUrl)
                .paymentType(PaymentEnums.VNPAY)
                .status(PaymentStatus.PENDING)
                .build();
    }

    @Override
    public PaymentResponse paymentWithBankTransfer(PaymentRequest paymentRequest) {
        return PaymentResponse
                .builder()
                .urlPayment(null)
                .paymentType(PaymentEnums.BANKING)
                .status(PaymentStatus.PENDING)
                .build();
    }

    @Override
    public PaymentResponse paymentWithCOD(PaymentRequest paymentRequest) {
        return PaymentResponse
                .builder()
                .urlPayment(null)
                .paymentType(PaymentEnums.COD)
                .status(PaymentStatus.PENDING)
                .build();
    }

    @Override
    public List<PaymentMethodResponse> getPaymentMethods() {
        List<PaymentMethodDAO> paymentMethodDAOS = paymentMethodRepository.findAll();
        List<PaymentMethodResponse> paymentMethods = new ArrayList<>();
        paymentMethodDAOS.forEach(v -> {
            paymentMethods.add(PaymentMethodResponse
                    .builder()
                    .paymentMethodDescription(v.getPaymentDescription())
                    .paymentMethodId(v.getPaymentId())
                    .paymentMethodName(v.getPaymentName())
                    .build());
        });
        return paymentMethods;
    }

    @Override
    public PaymentMethodResponse createPaymentMethod(CreatePaymentMethod createPaymentMethod) {
        PaymentMethodDAO paymentMethodDAO = PaymentMethodDAO
                .builder()
                .paymentDescription(createPaymentMethod.getPaymentMethodDescription())
                .paymentName(createPaymentMethod.getPaymentMethodName())
                .build();
        PaymentMethodDAO paymentMethodDAOSave = paymentMethodRepository.save(paymentMethodDAO);
        return PaymentMethodResponse
                .builder()
                .paymentMethodName(paymentMethodDAOSave.getPaymentName())
                .paymentMethodDescription(paymentMethodDAOSave.getPaymentDescription())
                .paymentMethodId(paymentMethodDAOSave.getPaymentId())
                .build();
    }
}