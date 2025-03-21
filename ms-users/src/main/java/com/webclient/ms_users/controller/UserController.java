package com.webclient.ms_users.controller;

import com.webclient.ms_users.domain.User;
import com.webclient.ms_users.dto.UserDTO;
import com.webclient.ms_users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // Endpoint para obtener un usuario por su ID
    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    // Endpoint para obtener todos los usuarios por ID de categoría
    @GetMapping("/category/{categoryId}")  // Cambio de ruta aquí
    public Mono<List<User>> getUsersByCategoryId(@PathVariable Integer categoryId) {
        return service.getUsersByCategoryId(categoryId);
    }
}


