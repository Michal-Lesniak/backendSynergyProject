package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.dto.CategoryDto;
import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.Category;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.services.CategoryService;
import com.example.backendsynergyproject.services.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("version")
@CrossOrigin
public class VersionController {

    @Autowired
    private VersionService versionService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Version>> getAllVersions() {
        return ResponseEntity.ok().body(versionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Version> getOneVersion(@PathVariable(name = "id") Long id) {
        try {
            Version version = versionService.findOne(id);
            return ResponseEntity.ok().body(version);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<List<Category>> getAllCategoryFromVersion(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(categoryService.findAllFromVersion(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/{id}/category")
    public ResponseEntity<Version> addCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDtoBody) {
        try {
            return ResponseEntity.ok().body(categoryService.add(categoryDtoBody, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVersion(@PathVariable Long id) {
        if (versionService.delete(id)) {
            return ResponseEntity.ok().body(true);
        } else return ResponseEntity.badRequest().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Version> updateVersion(@PathVariable Long id, @RequestBody VersionDto versionDtoBody) {
        try {
            return ResponseEntity.ok().body(versionService.update(versionDtoBody, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
