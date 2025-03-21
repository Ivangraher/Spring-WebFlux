package com.webclient.ms_users.repository;

import com.webclient.ms_users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByCategoryId(Integer categoryId);
}
