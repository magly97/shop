package com.magly.shop.controllers;

import com.magly.shop.modules.Product;
import com.magly.shop.modules.UserAddress;
import com.magly.shop.modules.Users;
import com.magly.shop.services.OrderServiceImpl;
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

    @Autowired
    private OrderServiceImpl orderService;

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
    public ResponseEntity<?> addAddress(@RequestBody UserAddress address) {
        return userAddressService.addAddress(address);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody UserAddress address, @PathVariable Long id) {
        return userAddressService.updateAddress(address, id);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        return userAddressService.getAddressById(id);
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAddressList() {
        return userAddressService.getAddressList();
    }


    @GetMapping("/orders")
    public ResponseEntity<?> getUserOrderList() {
        return orderService.getUserOrderList();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/cart/remove/{productId}")
    public ResponseEntity<?> deleteProductFromOrder(@PathVariable Long productId) {
        return orderService.deleteProductFromOrder(productId);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<?> changeOrderToBought(@PathVariable Long id) {
        return orderService.changeOrderToBought(id);
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getCart() {
        return orderService.getCart();
    }

}
