package com.bookstore.api.controller;

import com.bookstore.api.entity.Category;
import com.bookstore.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
	   @Autowired
	    private CategoryService categoryService;

	    // GET /api/categories — get all categories
	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryService.getAllCategories();
	    }

	    // GET /api/categories/{id} — get one category
	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        Optional<Category> category = categoryService.getCategoryById(id);
	        if (category.isPresent()) {
	            return ResponseEntity.ok(category.get());
	        }
	        return ResponseEntity.notFound().build();
	    }

	    // POST /api/categories — create new category
	    @PostMapping
	    public Category createCategory(@RequestBody Category category) {
	        return categoryService.createCategory(category);
	    }

	    // PUT /api/categories/{id} — update existing category
	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	        Category updated = categoryService.updateCategory(id, category);
	        if (updated != null) {
	            return ResponseEntity.ok(updated);
	        }
	        return ResponseEntity.notFound().build();
	    }

	    // DELETE /api/categories/{id} — delete category
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	        return ResponseEntity.noContent().build();
	    }
}
