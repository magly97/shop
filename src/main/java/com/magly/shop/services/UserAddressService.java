package com.magly.shop.services;

import com.magly.shop.modules.UserAddress;
import org.springframework.http.ResponseEntity;

public interface UserAddressService {

    ResponseEntity<?> deleteAddress(Long id);

    ResponseEntity<?> addAddress(UserAddress address);

    ResponseEntity<?> updateAddress(UserAddress address,Long id);

    ResponseEntity<?> getAddressById(Long id);

    ResponseEntity<?> getAddressList();

}
