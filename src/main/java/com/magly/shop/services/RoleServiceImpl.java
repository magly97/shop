package com.magly.shop.services;

import com.magly.shop.modules.Role;
import com.magly.shop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void initRoles() {
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role role = new Role("ROLE_USER");
            roleRepository.saveAndFlush(role);
        }
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role role = new Role("ROLE_ADMIN");
            roleRepository.saveAndFlush(role);
        }
    }
}
