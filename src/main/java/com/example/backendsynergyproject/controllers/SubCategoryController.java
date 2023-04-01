package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.dto.SubCategoryDto;
import com.example.backendsynergyproject.models.Category;
import com.example.backendsynergyproject.models.SubCategory;
import com.example.backendsynergyproject.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("subcategory")
public class SubCategoryController {

    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategory(){
        return ResponseEntity.ok().body(subCategoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getOneSubCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(subCategoryService.getOne(id));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(subCategoryService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryDto subCategoryDtoBody){
        try {
            return ResponseEntity.ok().body(subCategoryService.update(subCategoryDtoBody, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
