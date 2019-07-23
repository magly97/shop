package com.magly.shop.repositories;

import com.magly.shop.modules.UserAddress;
import com.magly.shop.modules.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Set<UserAddress> findAllByUserAddress(Users user);
}
