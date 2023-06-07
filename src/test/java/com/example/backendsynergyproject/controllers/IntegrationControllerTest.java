package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.controllers.IntegrationController;
import com.example.backendsynergyproject.dto.IntegrationDto;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.services.IntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


public class IntegrationControllerTest {
    @Mock
    private IntegrationService integrationService;

    @InjectMocks
    private IntegrationController integrationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIntegrations() {
        List<Integration> integrations = new ArrayList<>();
        integrations.add(new Integration());
        when(integrationService.findAll()).thenReturn(integrations);
        ResponseEntity<List<Integration>> response = integrationController.getIntegrations();
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetOneIntegration() throws Exception {
        when(integrationService.findOne(anyLong())).thenReturn(new Integration());
        ResponseEntity<Integration> response = integrationController.getOneIntegration(1L);
        assertNotNull(response.getBody());
    }

    @Test
    void testAddIntegration() throws Exception {
        when(integrationService.add(any(IntegrationDto.class))).thenReturn(new Integration());
        ResponseEntity<Integration> response = integrationController.addIntegration(new IntegrationDto("TestIntegration", 1000, 5));
        assertNotNull(response.getBody());
    }

    @Test
    void testAddIntegration_ExceptionThrown() throws Exception {
        Mockito.doThrow(new Exception("Test exception")).when(integrationService).add(any(IntegrationDto.class));

        ResponseEntity<Integration> response = integrationController.addIntegration(new IntegrationDto("TestIntegration", 1000, 5));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Mockito.verify(integrationService, Mockito.times(1)).add(any(IntegrationDto.class));
    }



}
