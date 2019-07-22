package com.magly.shop.services;

import com.magly.shop.modules.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> createCategory(Category category);

    ResponseEntity<?> deleteCategory(Long id);

    ResponseEntity<?> updateCategory(Category category, Long id);

    ResponseEntity<?> getCategoryList();

    ResponseEntity<?> getCategory(Long id);

}
