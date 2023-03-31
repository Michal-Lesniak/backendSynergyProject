package com.example.backendsynergyproject.repositories;

import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IntegrationRepository extends JpaRepository<Integration, Long> {
    boolean existsByName(String name);
    Optional<Integration>findByName(String name);
    void deleteById(Long id);
}
