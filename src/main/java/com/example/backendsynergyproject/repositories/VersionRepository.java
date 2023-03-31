package com.example.backendsynergyproject.repositories;

import com.example.backendsynergyproject.models.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionRepository extends JpaRepository<Version,Long> {
    List<Version> findAllByIntegration_Id(Long integration_id);
}
