package com.example.backendsynergyproject.repositories;

import com.example.backendsynergyproject.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
}
