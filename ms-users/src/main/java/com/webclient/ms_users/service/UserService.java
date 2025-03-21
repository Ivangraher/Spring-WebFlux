package com.webclient.ms_users.service;

import com.webclient.ms_users.domain.User;
import com.webclient.ms_users.dto.UserDTO;
import com.webclient.ms_users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private WebClient client;

    public Mono<UserDTO> getUserById(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String categoryName = client.get()
                .uri("http://localhost:8082/categories/" + user.getCategoryId()).retrieve()
                .bodyToMono(String.class)
                .block();  // .block() es adecuado en un contexto sincrónico

        user.setCategoryName(categoryName);
        return Mono.just(new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getEmail(), categoryName));
    }

    public Mono<List<User>> getUsersByCategoryId(Integer categoryId) {
        return Mono.fromCallable(() -> repository.findByCategoryId(categoryId));  // Aquí si necesitas envolver en Mono
    }

}



