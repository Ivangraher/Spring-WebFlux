package com.example.ms_categories.repository;
import com.example.ms_categories.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
