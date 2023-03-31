package com.example.backendsynergyproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer fullCost;

    @NotNull
    private Integer spendPercentOfBudgetCategory;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private Version version;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubCategory> subCategoryList = new ArrayList<>();

    public void addSubCategory(SubCategory subCategory){
        subCategoryList.add(subCategory);
        subCategory.setCategory(this);
    }
}
