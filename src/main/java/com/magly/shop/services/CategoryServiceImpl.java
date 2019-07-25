package com.magly.shop.services;

import com.magly.shop.message.response.ResponseMessage;
import com.magly.shop.modules.Category;
import com.magly.shop.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Fail -> category already exist"));
        } else {
            categoryRepository.saveAndFlush(category);
            return ResponseEntity.ok(new ResponseMessage("Category added"));
        }
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            return ResponseEntity.ok(new ResponseMessage("Category don't exist"));
        }
        Category category = categoryRepository.findById(id).get();
        category.getProducts().forEach(p -> p.getCategories().remove(category));
        categoryRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseMessage("Category deleted"));
    }

    @Override
    public ResponseEntity<?> getCategoryList() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getCategory(Long id) {
        return ResponseEntity.ok(categoryRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category, Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: product not find."));

        if (!categoryRepository.existsByName(category.getName())) {
            BeanUtils.copyProperties(category, existingCategory);
            categoryRepository.saveAndFlush(existingCategory);
            return new ResponseEntity<>(new ResponseMessage("Category updated successfully!"), HttpStatus.OK);
        } else if (existingCategory.getName().equals(category.getName())) {
            return new ResponseEntity<>(new ResponseMessage("Nothing changed at all"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Another category have same name"), HttpStatus.BAD_REQUEST);
        }
    }
}
