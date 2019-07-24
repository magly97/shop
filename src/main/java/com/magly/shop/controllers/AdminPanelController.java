package com.magly.shop.controllers;

import com.magly.shop.modules.Category;
import com.magly.shop.services.CategoryServiceImpl;
import com.magly.shop.services.OrderServiceImpl;
import com.magly.shop.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/admin")
public class AdminPanelController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/category")
    public ResponseEntity<?> getCategoryList() {
        return categoryService.getCategoryList();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.updateCategory(category, id);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserList() {
        return userService.getUserList();
    }

    @PutMapping("/user/up/{id}")
    public ResponseEntity<?> userAddAdminRole(@PathVariable Long id) {
        return userService.userAddAdminRole(id);
    }

    @PutMapping("/user/down/{id}")
    public ResponseEntity<?> userRemoveAdminRole(@PathVariable Long id) {
        return userService.userRemoveAdminRole(id);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/orders/ship/{id}")
    public ResponseEntity<?> shippedOrder(@PathVariable Long id) {
        return orderService.shippedOrder(id);
    }


}
