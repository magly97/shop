package com.magly.shop.services;

import com.magly.shop.message.response.ResponseMessage;
import com.magly.shop.modules.Orders;
import com.magly.shop.modules.Product;
import com.magly.shop.modules.Users;
import com.magly.shop.repositories.OrdersRepository;
import com.magly.shop.repositories.ProductRepository;
import com.magly.shop.repositories.StatusRepository;
import com.magly.shop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StatusServiceImpl statusService;

    @Override
    public ResponseEntity<?> addProductToOrder(Long productId) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usersRepository.findByUsername(userDetails.getUsername()).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("User error"));
        }

        if (productRepository.findById(productId).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Product not exist"));
        }
        if (statusRepository.findByName("OPEN").isEmpty()) {
            statusService.initStatus();
        }

        Users user = usersRepository.findByUsername(userDetails.getUsername()).get();
        Product product = productRepository.findById(productId).get();
        Date date = new Date();

        Optional<Orders> order = ordersRepository.findByUserOrder(user).stream()
                .filter(orders -> orders.getStatus().getName().equals("OPEN"))
                .findFirst();

        if (order.isPresent()) {

            order.get().setPrice(order.get().getPrice() + product.getPrice());
            order.get().getProducts().add(product);
            order.get().setDateStatus(date);
            ordersRepository.saveAndFlush(order.get());
        } else {
            List<Product> products = new ArrayList<>();
            products.add(product);
            Orders newOrder = new Orders(statusRepository.findByName("OPEN").get(), date, user, products, product.getPrice());
            ordersRepository.saveAndFlush(newOrder);
        }
        return ResponseEntity.ok(new ResponseMessage("Product added to order"));
    }

    @Override
    public ResponseEntity<?> deleteProductFromOrder(Long productId) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usersRepository.findByUsername(userDetails.getUsername()).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("User error"));
        }
        if(productRepository.findById(productId).isEmpty()){
            return ResponseEntity.badRequest().body(new ResponseMessage("Product not found"));
        }
        Product product = productRepository.findById(productId).get();
        Users user = usersRepository.findByUsername(userDetails.getUsername()).get();
        Date date = new Date();

        Optional<Orders> order = ordersRepository.findByUserOrder(user).stream()
                .filter(o -> o.getStatus().getName().equals("OPEN"))
                .filter(o -> o.getProducts().contains(product))
                .findFirst();

        if (order.isPresent()) {
            order.get().getProducts().remove(product);
            order.get().setPrice(order.get().getPrice() - product.getPrice());
            order.get().setDateStatus(date);
            ordersRepository.saveAndFlush(order.get());
            return ResponseEntity.ok(new ResponseMessage("Product deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Product not found in the order"));
        }
    }


    @Override
    public ResponseEntity<?> getUserOrderList() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usersRepository.findByUsername(userDetails.getUsername()).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("User error"));
        }

        Users user = usersRepository.findByUsername(userDetails.getUsername()).get();

        return ResponseEntity.ok(ordersRepository.findByUserOrder(user));
    }

    @Override
    public ResponseEntity<?> getOrderById(Long id) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<Orders> order = ordersRepository.findById(id).stream()
                .filter(o -> o.getUserOrder().getUsername().equals(username))
                .findFirst();

        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Order not found"));
        }
    }

    @Override
    public ResponseEntity<?> changeOrderToBought(Long id) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<Orders> order = ordersRepository.findById(id).stream()
                .filter(o -> o.getUserOrder().getUsername().equals(username))
                .filter(o -> o.getStatus().getName().equals("OPEN"))
                .findFirst();

        if (statusRepository.findByName("IN_PROGRESS").isEmpty()) {
            statusService.initStatus();
        }

        Date date = new Date();

        if (order.isPresent()) {
            order.get().setStatus(statusRepository.findByName("IN_PROGRESS").get());
            order.get().setDateStatus(date);
            ordersRepository.saveAndFlush(order.get());
            return ResponseEntity.ok(new ResponseMessage("Order bought"));
        } else {
            return ResponseEntity.badRequest().body("nothing to change");
        }
    }

    @Override
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(ordersRepository.findAll());
    }

    @Override
    public ResponseEntity<?> shippedOrder(Long id) {

        if (statusRepository.findByName("SHIPPED").isEmpty()) {
            statusService.initStatus();
        }

        Optional<Orders> order = ordersRepository.findById(id).stream()
                .filter(o -> o.getStatus().getName().equals("IN_PROGRESS"))
                .findFirst();


        Date date = new Date();

        if (order.isPresent()) {
            order.get().setStatus(statusRepository.findByName("SHIPPED").get());
            order.get().setDateStatus(date);
            ordersRepository.saveAndFlush(order.get());
            return ResponseEntity.ok(new ResponseMessage("Order send"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Order not exist or have another status"));
        }
    }

    @Override
    public ResponseEntity<?> getCart() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usersRepository.findByUsername(userDetails.getUsername()).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("User error"));
        }
        if (statusRepository.findByName("OPEN").isEmpty()) {
            statusService.initStatus();
        }
        Users user = usersRepository.findByUsername(userDetails.getUsername()).get();

        Optional<Orders> cart = ordersRepository.findByUserOrder(user).stream()
                .filter(o -> o.getStatus().getName().equals("OPEN"))
                .findFirst();
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            Date date = new Date();
            List<Product> products = new ArrayList<>();
            Orders newCart = new Orders(statusRepository.findByName("OPEN").get(), date, user, products, 0.0);
            ordersRepository.saveAndFlush(newCart);
            return ResponseEntity.ok(newCart);
        }
    }
}
