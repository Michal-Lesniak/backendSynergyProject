package com.example.backendsynergyproject.dto;

import com.example.backendsynergyproject.models.Version;

import java.io.File;
import java.util.List;

public record IntegrationDto(
        String name,
        Integer budget,
        Integer noOfMembers
) {}
