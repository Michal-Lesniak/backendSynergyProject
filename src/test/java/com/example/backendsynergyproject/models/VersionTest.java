package com.example.backendsynergyproject.models;

import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.models.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {
    @Test
    void testVersion() {
        Version version = new Version();
        version.setPercentOfSpendBudget(50.5f);
        version.setName("TestVersion");
        version.addCategory(new Category());

        assertEquals(50.5f, version.getPercentOfSpendBudget());
        assertEquals("TestVersion", version.getName());
        assertEquals(1, version.getCategoryList().size());
    }
}
