package com.magly.shop.services;

import com.magly.shop.modules.UserAddress;
import org.springframework.http.ResponseEntity;

public interface UserAddressService {

    ResponseEntity<?> deleteAddress(Long id);

    ResponseEntity<?> addAddress(UserAddress addressForm);

    ResponseEntity<?> updateAddress(UserAddress address);

    ResponseEntity<?> getAddressById(Long id);

    ResponseEntity<?> getAddressList();

}
