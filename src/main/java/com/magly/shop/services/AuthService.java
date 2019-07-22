package com.magly.shop.services;

import com.magly.shop.message.request.LoginForm;
import com.magly.shop.message.request.SignUpForm;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authenticateUser(LoginForm loginRequest);

    ResponseEntity<?> registerUser(SignUpForm signUpRequest);

}