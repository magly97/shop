package com.magly.shop.services;

import com.magly.shop.message.response.ResponseMessage;
import com.magly.shop.modules.UserAddress;
import com.magly.shop.modules.Users;
import com.magly.shop.repositories.UserAddressRepository;
import com.magly.shop.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        if (userAddressRepository.existsById(id)) {
            userAddressRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage("Address deleted"));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("Address can't be deleted"));
    }

    @Override
    public ResponseEntity<?> addAddress(UserAddress address) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Users user = usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not exist"));
        address.setUserAddress(user);

        userAddressRepository.saveAndFlush(address);
        return ResponseEntity.ok(new ResponseMessage("address added"));
    }

    @Override
    public ResponseEntity<?> updateAddress(UserAddress address) {
        UserAddress existingAddress = userAddressRepository.findById(address.getId())
                .orElseThrow(() -> new RuntimeException("address not exist"));

        BeanUtils.copyProperties(address, existingAddress);
        userAddressRepository.saveAndFlush(existingAddress);
        return ResponseEntity.ok("Address edited");
    }

    @Override
    public ResponseEntity<?> getAddressById(Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Set<UserAddress> userAddressesSet = new HashSet<>();
        userAddressRepository.findById(id).stream()
                .filter(address -> address.getUserAddress().getUsername().equals(username))
                .forEach(userAddressesSet::add);

        if (userAddressesSet.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Address not exist"));
        } else {
            return ResponseEntity.ok(userAddressesSet);
        }
    }

    @Override
    public ResponseEntity<?> getAddressList() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Users user = usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User don't exist"));
        return ResponseEntity.ok(userAddressRepository.findAllByUserAddress(user));

    }
}
