package com.magly.shop.controllers;

import com.magly.shop.modules.Category;
import com.magly.shop.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/admin/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<?> getCategoryList() {
        return categoryService.getCategoryList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.updateCategory(category, id);
    }

}
