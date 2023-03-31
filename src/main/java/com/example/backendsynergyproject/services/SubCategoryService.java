package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.models.Category;
import com.example.backendsynergyproject.models.SubCategory;
import com.example.backendsynergyproject.repositories.CategoryRepository;
import com.example.backendsynergyproject.repositories.SubCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<SubCategory> getAll(){
        return subCategoryRepository.findAll();
    }

    public List<SubCategory> getAllFromVersion(Long category_id){
        return subCategoryRepository.findAllByCategory_Id(category_id);
    }

    public SubCategory getOne(Long id) throws Exception{
        return subCategoryRepository.findById(id).orElseThrow(()-> new Exception("SubCategory Not Found"));
    }


    @Transactional
    public Category add(SubCategory subCategoryBody, Long category_id) throws Exception{
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new Exception("Category Not Found"));
        category.addSubCategory(subCategoryBody);
        return categoryRepository.save(category);
    }

    @Transactional
    public Boolean delete(Long id){
        if(subCategoryRepository.existsById(id)){
            subCategoryRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public SubCategory update(SubCategory subCategoryBody, Long id) throws Exception{
        SubCategory updatedSubCategory = subCategoryRepository.findById(id).orElseThrow(() -> new Exception("Category Not Found"));
        updatedSubCategory.setName(subCategoryBody.getName());
        updatedSubCategory.setCost(subCategoryBody.getCost());
        return subCategoryRepository.save(updatedSubCategory);
    }

}
