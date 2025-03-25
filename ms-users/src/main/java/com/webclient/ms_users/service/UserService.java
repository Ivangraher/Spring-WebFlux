package com.webclient.ms_users.service;

import com.webclient.ms_users.domain.User;
import com.webclient.ms_users.dto.CategoryDTO;
import com.webclient.ms_users.dto.UserDTO;
import com.webclient.ms_users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private WebClient client;

    @Autowired
    private EmailService emailService;

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


   /* public void sendNotification(){
        List<User> userList = repository.findAll();
        List<User> filteredUsers = userList.stream()
                .filter(user -> user.getCategoryId() == 1)
                .toList();

        filteredUsers.forEach(user -> emailService.sendMessage(user.getEmail(), "Notificación", "Este es un mensaje de prueba"));
    }*/

    public void sendNotification() {
        List<User> userList = repository.findAll();

        // Filtrar los usuarios con categoryId = 1
        List<User> filteredUsers = userList.stream()
                .filter(user -> user.getCategoryId() == 1)
                .collect(Collectors.toList());

        // Enviar correo a cada usuario después de obtener el nombre de la categoría
        filteredUsers.forEach(user -> {
            String categoryName = fetchCategoryName(user.getCategoryId());
            String message = generateEmailMessage(user, categoryName);
            emailService.sendMessage(user.getEmail(), "Notificación Importante", message);
        });
    }

    private String fetchCategoryName(Integer categoryId) {
        return client.get()
                .uri("http://localhost:8082/categories/" + categoryId) // Ahora esta URI sí existe
                .retrieve()
                .bodyToMono(CategoryDTO.class) // Esperamos un objeto Category en la respuesta
                .map(CategoryDTO::getName) // Extraemos solo el nombre
                .block();
    }


    private String generateEmailMessage(User user, String categoryName) {
        return String.format(
                "<html><body style='font-family: Arial, sans-serif;'>" +
                        "<h2>Hola %s %s,</h2>" +
                        "<p>Tu información es la siguiente:</p>" +
                        "<ul>" +
                        "<li><b>📧 Correo:</b> %s</li>" +
                        "<li><b>🆔 ID de Categoría:</b> %d</li>" +
                        "<li><b>🏷️ Nombre de la Categoría:</b> %s</li>" +
                        "</ul>" +
                        "<p>Este es un mensaje de prueba.</p>" +
                        "<p><b>Saludos.</b></p>" +
                        "</body></html>",
                user.getName(), user.getLastname(), user.getEmail(), user.getCategoryId(), categoryName
        );
    }


    /*
    @PostConstruct
    public void init() {
        sendNotification();
    }
    */
}



