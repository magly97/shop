package com.magly.shop.services;

import com.magly.shop.message.response.ResponseMessage;
import com.magly.shop.modules.Role;
import com.magly.shop.modules.Users;
import com.magly.shop.repositories.RoleRepository;
import com.magly.shop.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> getUser() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(usersRepository.findByUsername(userDetails.getUsername()));
    }

    @Override
    public ResponseEntity<?> getUserList() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

    @Override
    public ResponseEntity<?> deleteUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        if (usersRepository.existsByUsername(username)) {
            usersRepository.findByUsername(username).ifPresent(user -> usersRepository.delete(user));
            return ResponseEntity.ok(new ResponseMessage("User deleted."));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("User not exist"));
    }

    @Override
    public ResponseEntity<?> updateUser(Users user) {

        if (usersRepository.findById(user.getId()).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Wrong form or user not exist"));
        }

        Users existingUser = usersRepository.findById(user.getId()).get();

        if (usersRepository.existsByUsername(user.getUsername()) && !existingUser.getUsername().equals(user.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Username already used."));
        }

        if (usersRepository.existsByEmail(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Email already used."));
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        BeanUtils.copyProperties(user, existingUser);
        usersRepository.saveAndFlush(existingUser);

        return ResponseEntity.ok(new ResponseMessage("Updated complete."));

    }

    @Override
    public ResponseEntity<?> userAddAdminRole(Long id) {
        if (usersRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("User not exist"));
        }
        Users user = usersRepository.findById(id).get();
        user.getRoles().add(roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Role not found")));
        usersRepository.saveAndFlush(user);
        return ResponseEntity.ok(new ResponseMessage("Admin role added"));
    }

    @Override
    public ResponseEntity<?> userRemoveAdminRole(Long id) {
        if (usersRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("User not exist"));
        }
        Users user = usersRepository.findById(id).get();
        user.getRoles().remove(roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Role not found")));
        usersRepository.saveAndFlush(user);
        return ResponseEntity.ok(new ResponseMessage("Admin role remove"));
    }
}
