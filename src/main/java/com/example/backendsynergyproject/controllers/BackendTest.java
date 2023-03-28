package com.example.backendsynergyproject.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BackendTest {

    @GetMapping("/")
    public boolean Test(){
        return true;
    }
}
