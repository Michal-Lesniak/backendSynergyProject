package com.example.backendsynergyproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer fullCost;

    @NotNull
    private Integer spendPercentOfBudgetCategory;

    @JsonIgnoreProperties("versionList")
    @ManyToOne
    @JoinColumn(name = "version_id")
    private Version version;

    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubCategory> subCategoryList = new ArrayList<>();

    public void addSubCategory(SubCategory subCategory){
        subCategoryList.add(subCategory);
        subCategory.setCategory(this);
    }
}
