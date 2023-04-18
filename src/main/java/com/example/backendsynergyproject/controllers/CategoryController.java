package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.dto.CategoryDto;
import com.example.backendsynergyproject.dto.SubCategoryDto;
import com.example.backendsynergyproject.models.Category;
import com.example.backendsynergyproject.models.SubCategory;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.repositories.SubCategoryRepository;
import com.example.backendsynergyproject.services.CategoryService;
import com.example.backendsynergyproject.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOneCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(categoryService.findOne(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/subcategory")
    public ResponseEntity<List<SubCategory>> getAllSubCategoryFromCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(subCategoryService.getAllFromVersion(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/subcategory")
    public ResponseEntity<SubCategory> addSubCategory(@PathVariable Long id, @RequestBody SubCategoryDto subCategoryDtoBody){
        try {
            List<SubCategory> subCategoryList = subCategoryService.add(subCategoryDtoBody, id).getSubCategoryList();
            SubCategory addedSubCategory = subCategoryList.get(subCategoryList.size() - 1);
            return ResponseEntity.ok().body(addedSubCategory);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id) {
        if (categoryService.delete(id)) {
            return ResponseEntity.ok().body(true);
        } else return ResponseEntity.badRequest().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDtoBody) {
        try {
            return ResponseEntity.ok().body(categoryService.update(categoryDtoBody, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
