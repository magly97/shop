package com.magly.shop.repositories;

import com.magly.shop.modules.Orders;
import com.magly.shop.modules.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserOrder(Users user);
}
