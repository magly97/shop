package com.magly.shop.services;

import com.magly.shop.message.request.ProductForm;
import com.magly.shop.modules.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> createProduct(ProductForm productForm);

    ResponseEntity<?> updateProduct(Product product);

    ResponseEntity<?> deleteProduct(Long id);

    ResponseEntity<?> getProductList();

    ResponseEntity<?> getProduct(Long id);

}
