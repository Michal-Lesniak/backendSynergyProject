package com.example.backendsynergyproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Integer id;
    private String subcategories;
}
