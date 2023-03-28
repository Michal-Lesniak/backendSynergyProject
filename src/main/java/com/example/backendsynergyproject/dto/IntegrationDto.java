package com.example.backendsynergyproject.dto;

import com.example.backendsynergyproject.models.Version;

import java.io.File;
import java.util.List;

public record IntegrationDto(
        String name,
        String photo,
        Integer budget,
        Integer noOfMembers,
        List<Version> versionList
) {}
