package com.example.backendsynergyproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Integration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String photo;
    @NotNull
    private Integer budget;
    @NotNull
    private Integer noOfMembers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "integration_id")
    private List<Version> versionList = new ArrayList<>();

}
