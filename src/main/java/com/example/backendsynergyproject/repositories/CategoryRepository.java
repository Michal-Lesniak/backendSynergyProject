package com.example.backendsynergyproject.repositories;

import com.example.backendsynergyproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
