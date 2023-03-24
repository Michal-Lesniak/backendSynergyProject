package com.example.backendsynergyproject.models;

import jakarta.persistence.*;


import java.io.File;
import java.util.Set;

@Entity
@Table(name = "integrations")
public class Integration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private File photo;
    private Integer budget;
    private Integer noOfMembers;

//    @OneToMany(mappedBy = "integrations")
//    private Set<Category> categories;
}
