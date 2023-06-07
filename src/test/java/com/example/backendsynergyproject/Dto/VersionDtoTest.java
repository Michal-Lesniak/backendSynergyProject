package com.example.backendsynergyproject.Dto;

import com.example.backendsynergyproject.dto.VersionDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VersionDtoTest {
    @Test
    void testVersionDto() {
        VersionDto versionDto = new VersionDto(50.5f, "TestVersion");
        assertEquals(50.5f, versionDto.percentOfSpendBudget());
        assertEquals("TestVersion", versionDto.name());
    }
}
