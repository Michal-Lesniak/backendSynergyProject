package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.IntegrationDto;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.repositories.IntegrationRepository;
import com.example.backendsynergyproject.services.IntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class IntegrationServiceTest {

    @Mock
    private IntegrationRepository integrationRepository;

    @InjectMocks
    private IntegrationService integrationService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindOneById() {
        when(integrationRepository.findById(1L)).thenReturn(Optional.of(new Integration(1L, "Integration1", 1000, 5, null)));
        Integration integration = integrationService.findOne(1L);
        assertNotNull(integration);
        assertEquals("Integration1", integration.getName());
    }

    @Test
    void testFindOneById_NotFound() {
        when(integrationRepository.findById(1L)).thenReturn(Optional.empty());
        Integration integration = integrationService.findOne(1L);
        assertNull(integration);
    }

    @Test
    void testAddIntegration_AlreadyExists() {
        IntegrationDto dto = new IntegrationDto("Integration1", 1000, 5);
        when(integrationRepository.existsByName("Integration1")).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            integrationService.add(dto);
        });
        assertEquals("Integracja Istnieje", exception.getMessage());
    }

    @Test
    void testAddIntegration_Success() throws Exception {
        IntegrationDto dto = new IntegrationDto("Integration2", 2000, 10);
        when(integrationRepository.existsByName("Integration2")).thenReturn(false);
        when(integrationRepository.save(any())).thenReturn(new Integration());
        Integration addedIntegration = integrationService.add(dto);
        assertNotNull(addedIntegration);
    }
}

