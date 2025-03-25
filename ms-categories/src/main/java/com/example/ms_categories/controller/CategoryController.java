package com.example.ms_categories.controller;

import com.example.ms_categories.domain.Category;
import com.example.ms_categories.domain.User;
import com.example.ms_categories.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/all")
    public Flux<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{categoryId}/users")
    public Flux<User> getUsersByCategoryId(@PathVariable Integer categoryId) {
        return service.getUsersByCategoryId(categoryId);
    }

    @GetMapping("/{categoryId}")
    public Mono<Category> getCategoryById(@PathVariable Integer categoryId) {
        return service.getCategoryById(categoryId);
    }

}

