package com.magly.shop.services;

import com.magly.shop.modules.Status;
import com.magly.shop.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NamingEnumeration;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void initStatus() {
        if (statusRepository.findByName("OPEN").isEmpty()) {
            Status status = new Status("OPEN");
            statusRepository.saveAndFlush(status);
        }
        if (statusRepository.findByName("IN_PROGRESS").isEmpty()) {
            Status status = new Status("IN_PROGRESS");
            statusRepository.saveAndFlush(status);
        }
        if (statusRepository.findByName("SHIPPED").isEmpty()) {
            Status status = new Status("SHIPPED");
            statusRepository.saveAndFlush(status);
        }
    }
}
