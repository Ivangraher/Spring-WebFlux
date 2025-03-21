package com.example.ms_categories.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private Integer categoryId;
}
