package com.magly.shop.services;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magly.shop.message.request.LoginForm;
import com.magly.shop.message.request.SignUpForm;
import com.magly.shop.message.response.JwtResponse;
import com.magly.shop.message.response.ResponseMessage;
import com.magly.shop.modules.Role;
import com.magly.shop.modules.Users;
import com.magly.shop.repositories.RoleRepository;
import com.magly.shop.repositories.UsersRepository;
import com.magly.shop.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private RoleService roleService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Fail -> Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Fail -> Email is already in use!"));
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty() || roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleService.initRoles();
        }


        // Creating user's account
        Users user = new Users(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        try {
            Set<String> strRoles = signUpRequest.getRole();
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName("ROLE_USER").get();
                    roles.add(userRole);
                }
            });
        } catch (Exception e) {
            roles.add(roleRepository.findByName("ROLE_USER").get());
        }

        user.setRoles(roles);
        userRepository.saveAndFlush(user);

        return ResponseEntity.ok(new ResponseMessage("User registered successfully!"));
    }
}
