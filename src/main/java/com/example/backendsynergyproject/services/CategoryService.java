package com.example.backendsynergyproject.services;

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
            return categoryRepository.findAllByVersion_Id(version_id);
        } else {
            throw new Exception("Version Not Found");
        }
    }

    public Category findOne(Long id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(() -> new Exception("Category Not Found"));
    }

//    @Transactional
//    public Version add(Category categoryBody, Long version_id) throws Exception {
//        Version version = versionRepository.findById(version_id).orElseThrow(() -> new Exception("Version Not Found"));
//        version.addCategory(categoryBody);
//        return versionRepository.save(version);
//    }

    @Transactional
    public Version add(Category categoryBody, Long version_id) throws Exception{
        Optional<Version> version = versionRepository.findById(version_id);
        if(version.isEmpty()){
            throw new Exception("Version Not Found");
        }else {
            Version versionToUpdate = version.get();
            versionToUpdate.addCategory(categoryBody);
            return versionRepository.save(versionToUpdate);
        }

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
    public Category update(Category categoryBody, Long id) throws Exception {
        Category updatedCategory = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category Not Found"));
        updatedCategory.setName(categoryBody.getName());
        updatedCategory.setFullCost(categoryBody.getFullCost());
        updatedCategory.setSpendPercentOfBudgetCategory(categoryBody.getFullCost());
        return categoryRepository.save(updatedCategory);

    }

}
