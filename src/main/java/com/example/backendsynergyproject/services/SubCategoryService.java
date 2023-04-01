package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.CategoryDto;
import com.example.backendsynergyproject.dto.SubCategoryDto;
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
        return categoryRepository.findById(category_id).get().getSubCategoryList();
    }

    public SubCategory getOne(Long id) throws Exception{
        return subCategoryRepository.findById(id).orElseThrow(()-> new Exception("SubCategory Not Found"));
    }


    @Transactional
    public Category add(SubCategoryDto subCategoryDtoBody, Long category_id) throws Exception{
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new Exception("Category Not Found"));
        category.addSubCategory(mapToSubCategory(subCategoryDtoBody));
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
    public SubCategory update(SubCategoryDto subCategoryDtoBody , Long id) throws Exception{
        SubCategory updatedSubCategory = subCategoryRepository.findById(id).orElseThrow(() -> new Exception("Category Not Found"));
        updatedSubCategory.setName(subCategoryDtoBody.name());
        updatedSubCategory.setCost(subCategoryDtoBody.cost());
        return subCategoryRepository.save(updatedSubCategory);
    }

    public SubCategoryDto mapToDto(SubCategory subCategory){
        return new SubCategoryDto(subCategory.getName(), subCategory.getCost());
    }

    public SubCategory mapToSubCategory(SubCategoryDto subCategoryDto){
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryDto.name());
        subCategory.setCost(subCategoryDto.cost());
        return subCategory;
    }

}
