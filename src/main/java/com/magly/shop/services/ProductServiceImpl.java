package com.magly.shop.services;

import com.magly.shop.message.request.ProductForm;
import com.magly.shop.modules.Product;
import com.magly.shop.repositories.CategoryRepository;
import com.magly.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


}
