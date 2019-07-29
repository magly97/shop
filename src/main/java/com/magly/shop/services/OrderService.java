package com.magly.shop.services;

import com.magly.shop.modules.Product;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> addProductToOrder(Long id);

    ResponseEntity<?> deleteProductFromOrder(Long productId);

    ResponseEntity<?> getUserOrderList();

    ResponseEntity<?> getOrderById(Long id);

    ResponseEntity<?> changeOrderToBought(Long id);

    ResponseEntity<?> getAllOrders();

    ResponseEntity<?> shippedOrder(Long id);

    ResponseEntity<?> getCart();


}
