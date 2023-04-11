package com.example.backendsynergyproject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 10000)
    private byte[] imageData;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "integration_id")
    private Integration integration;


}

