package com.magly.shop.controllers;

import com.magly.shop.modules.UserAddress;
import com.magly.shop.modules.Users;
import com.magly.shop.services.UserAddressServiceImpl;
import com.magly.shop.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserAddressServiceImpl userAddressService;

    @GetMapping
    public ResponseEntity<?> getUserByUsername() {
        return userService.getUser();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        return userService.deleteUser();
    }

    @PutMapping
    public ResponseEntity<?> updateUserName(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        return userAddressService.deleteAddress(id);
    }

    @PostMapping("/address")
    public ResponseEntity<?> addAddress(UserAddress address) {
        return userAddressService.addAddress(address);
    }

    @PutMapping("/address")
    public ResponseEntity<?> updateAddress(UserAddress address) {
        return userAddressService.updateAddress(address);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        return userAddressService.getAddressById(id);
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAddressList() {
        return userAddressService.getAddressList();
    }

}
