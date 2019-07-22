package com.magly.shop.services;

import com.magly.shop.message.request.ProductForm;
import com.magly.shop.message.response.ResponseMessage;
import com.magly.shop.modules.Category;
import com.magly.shop.modules.Product;
import com.magly.shop.repositories.CategoryRepository;
import com.magly.shop.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> createProduct(ProductForm productForm) {
        Product product = new Product(productForm.getName(), productForm.getPrice());

        Set<String> formSet = productForm.getCategory();
        Set<Category> categorySet = new HashSet<>();
        if (!formSet.isEmpty()) {
            formSet.forEach(category -> categorySet.add(categoryRepository.findByName(category)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: category " + category + " not find."))));
        }
        product.setCategories(categorySet);
        productRepository.saveAndFlush(product);

        return new ResponseEntity<>(new ResponseMessage("Product create successfully!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product, Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: product not find."));

        BeanUtils.copyProperties(product, existingProduct);
        productRepository.saveAndFlush(existingProduct);

        return new ResponseEntity<>(new ResponseMessage("Product updated successfully!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProductList() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getProduct(Long id) {
        if (productRepository.existsById(id)) {
            return ResponseEntity.ok(productRepository.findById(id));
        } else {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Product is not exist"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("Product deleted"), HttpStatus.OK);
    }

}
