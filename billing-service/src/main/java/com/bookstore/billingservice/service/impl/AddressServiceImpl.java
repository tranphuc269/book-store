package com.bookstore.billingservice.service.impl;

import com.bookstore.billingservice.common.util.CommonUtilityMethods;
import com.bookstore.billingservice.dao.AddressDao;
import com.bookstore.billingservice.repository.AddressRepository;
import com.bookstore.billingservice.service.AddressService;
import com.bookstore.billingservice.vo.request.CreateAddressRequest;
import com.bookstore.billingservice.vo.request.UpdateAddressRequest;
import com.bookstore.billingservice.vo.response.GetAddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void createAddress(CreateAddressRequest createAddressRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        addressRepository.save(AddressDao
                .builder()
                .userId(userIdFromToken)
                .userName(createAddressRequest.getUserName())
                .phoneNumber(createAddressRequest.getPhoneNumber())
                .city(createAddressRequest.getCity())
                .district(createAddressRequest.getDistrict())
                .detail(createAddressRequest.getDetail())
                .province(createAddressRequest.getProvince())
                .build());
    }

    @Override
    public List<GetAddressResponse> getAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        Optional<List<AddressDao>> addressDaos = addressRepository.findByUserId(userIdFromToken);
        if (addressDaos.isEmpty()) {
            return new ArrayList<>();
        }
        List<GetAddressResponse> addressResponses = new ArrayList<>();
        addressDaos.get().forEach(v -> {
            addressResponses.add(GetAddressResponse
                    .builder()
                    .addressId(v.getAddressId())
                    .userName(v.getUserName())
                    .phoneNumber(v.getPhoneNumber())
                    .city(v.getCity())
                    .district(v.getDistrict())
                    .province(v.getProvince())
                    .userId(v.getUserId())
                    .detail(v.getDetail())
                    .build());
        });
        return addressResponses;
    }

    @Override
    public void updateAddress(UpdateAddressRequest updateAddressRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        Optional<AddressDao> addressDao = addressRepository.findByAddressId(updateAddressRequest.getAddressId());
        if (addressDao.isPresent()) {
            AddressDao addressDaoPresent = AddressDao
                    .builder()
                    .userId(userIdFromToken)
                    .userName(updateAddressRequest.getUserName())
                    .phoneNumber(updateAddressRequest.getPhoneNumber())
                    .addressId(updateAddressRequest.getAddressId())
                    .city(updateAddressRequest.getCity())
                    .district(updateAddressRequest.getDistrict())
                    .province(updateAddressRequest.getProvince())
                    .detail(updateAddressRequest.getDetail())
                    .build();
            addressRepository.save(addressDaoPresent);
        }
    }

    @Override
    public GetAddressResponse getAddressById(String addressId) {
        Optional<AddressDao> addressDao = addressRepository.findByAddressId(addressId);
        return addressDao.map(dao -> GetAddressResponse
                .builder()
                .userId(dao.getUserId())
                .userName(dao.getUserName())
                .phoneNumber(dao.getPhoneNumber())
                .addressId(dao.getAddressId())
                .city(dao.getCity())
                .district(dao.getDistrict())
                .province(dao.getProvince())
                .detail(dao.getDetail())
                .build()).orElse(null);
    }

    @Override
    public void deleteAddressById(String addressId) {
        addressRepository.deleteByAddressId(addressId);
    }
}
