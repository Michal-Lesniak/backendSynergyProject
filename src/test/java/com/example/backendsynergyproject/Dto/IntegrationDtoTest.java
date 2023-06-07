package com.example.backendsynergyproject.Dto;


import com.example.backendsynergyproject.dto.IntegrationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationDtoTest {

    @Test
    void testIntegrationDto() {
        IntegrationDto integrationDto = new IntegrationDto("TestIntegration", 1000, 5);
        assertEquals("TestIntegration", integrationDto.name());
        assertEquals(1000, integrationDto.budget());
        assertEquals(5, integrationDto.noOfMembers());
    }

}
