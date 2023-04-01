package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.CategoryDto;
import com.example.backendsynergyproject.models.Category;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.repositories.CategoryRepository;
import com.example.backendsynergyproject.repositories.VersionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    VersionRepository versionRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllFromVersion(Long version_id) throws Exception {
        if (versionRepository.existsById(version_id)) {
            return versionRepository.findById(version_id).get().getCategoryList();
        } else {
            throw new Exception("Version Not Found");
        }
    }

    public Category findOne(Long id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(() -> new Exception("Category Not Found"));
    }

    @Transactional
    public Version add(CategoryDto categoryDtoBody, Long version_id) throws Exception {
        Version version = versionRepository.findById(version_id).orElseThrow(() -> new Exception("Version Not Found"));
        version.addCategory(mapToCategory(categoryDtoBody));
        return versionRepository.save(version);
    }



    @Transactional
    public Boolean delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Category update(CategoryDto categoryDtoBody, Long id) throws Exception {
        Category updatedCategory = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category Not Found"));
        updatedCategory.setName(categoryDtoBody.name());
        updatedCategory.setFullCost(categoryDtoBody.fullCost());
        updatedCategory.setSpendPercentOfBudgetCategory(categoryDtoBody.spendPercentOfBudgetCategory());
        return categoryRepository.save(updatedCategory);

    }

    public CategoryDto mapToDto(Category category){
        return new CategoryDto(category.getName(), category.getFullCost(), category.getSpendPercentOfBudgetCategory());
    }

    public Category mapToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.name());
        category.setFullCost(categoryDto.fullCost());
        category.setSpendPercentOfBudgetCategory(categoryDto.spendPercentOfBudgetCategory());
        return category;
    }

}
