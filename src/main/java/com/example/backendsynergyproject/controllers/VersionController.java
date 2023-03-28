package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.services.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @GetMapping
    public ResponseEntity<List<Version>> getAll(){
        List<Version> versionList = versionService.findAll();
        return ResponseEntity.ok().body(versionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Version> getOne(@PathVariable(name = "id") Long id){
        Version version = versionService.findOne(id);
        if(version != null){
            return ResponseEntity.ok().body(version);
        }
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Version> addOne(@RequestBody VersionDto versionBody){
        try {
            Version version = versionService.addVersion(versionService.mapToVersion(versionBody));
            System.out.println(version);
            return ResponseEntity.ok().body(version);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
