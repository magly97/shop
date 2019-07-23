package com.magly.shop.services;

import com.magly.shop.modules.Users;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> getUser();

    ResponseEntity<?> getUserList();

    ResponseEntity<?> deleteUser();

    ResponseEntity<?> updateUser(Users user);

}
