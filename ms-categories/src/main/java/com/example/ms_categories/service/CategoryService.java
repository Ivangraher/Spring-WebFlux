package com.example.ms_categories.service;

import com.example.ms_categories.domain.Category;
import com.example.ms_categories.domain.User;
import com.example.ms_categories.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private WebClient client;

    // Método para obtener todas las categorías (no bloqueante)
    public Flux<Category> getAllCategories() {
        return Flux.fromIterable(repository.findAll());
    }

    // Método reactivo para obtener usuarios por ID de categoría
    public Flux<User> getUsersByCategoryId(Integer categoryId) {
        String url = "http://localhost:8080/users/category/" + categoryId; // Endpoint en el microservicio de usuarios

        // Se devuelve un Flux<User> de manera reactiva
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Mono<Category> getCategoryById(Integer categoryId) {
        return Mono.justOrEmpty(repository.findById(categoryId))
                .map(category -> new Category(category.getId(), category.getName()))
                .switchIfEmpty(Mono.error(new RuntimeException("Categoría no encontrada")));
    }



}
