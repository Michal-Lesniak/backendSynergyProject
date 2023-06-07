package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.repositories.IntegrationRepository;
import com.example.backendsynergyproject.repositories.VersionRepository;
import com.example.backendsynergyproject.services.VersionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VersionServiceTest {

    @Mock
    private VersionRepository versionRepository;

    @Mock
    private IntegrationRepository integrationRepository;

    @InjectMocks
    private VersionService versionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Version> versionList = new ArrayList<>();
        versionList.add(new Version());
        when(versionRepository.findAll()).thenReturn(versionList);
        List<Version> versions = versionService.findAll();
        assertEquals(1, versions.size());
    }

    @Test
    void testFindOneById() throws Exception {
        when(versionRepository.findById(1L)).thenReturn(Optional.of(new Version()));
        Version version = versionService.findOne(1L);
        assertNotNull(version);
    }

    @Test
    void testFindOneById_NotFound() {
        when(versionRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> {
            versionService.findOne(1L);
        });
        assertEquals("Version Not Found", exception.getMessage());
    }

    @Test
    void testAddIntegration_Success() throws Exception {
        VersionDto dto = new VersionDto(0.5F, "Version1");
        when(integrationRepository.findById(1L)).thenReturn(Optional.of(new Integration()));
        when(integrationRepository.save(any())).thenReturn(new Integration());
        Integration addedIntegration = versionService.add(dto, 1L);
        assertNotNull(addedIntegration);
    }

    @Test
    void testAddIntegration_IntegrationNotFound() {
        VersionDto dto = new VersionDto(0.5F, "Version1");
        when(integrationRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> {
            versionService.add(dto, 1L);
        });
        assertEquals("Taka integracja nie istnieje!", exception.getMessage());
    }

    @Test
    void testDelete_VersionExists() {
        when(versionRepository.existsById(1L)).thenReturn(true);
        boolean result = versionService.delete(1L);
        assertTrue(result);
    }

    @Test
    void testDelete_VersionDoesntExist() {
        when(versionRepository.existsById(1L)).thenReturn(false);
        boolean result = versionService.delete(1L);
        assertFalse(result);
    }

    @Test
    void testUpdate_VersionExists() throws Exception {
        VersionDto dto = new VersionDto(0.5F, "Version1");
        when(versionRepository.findById(1L)).thenReturn(Optional.of(new Version()));
        Version updatedVersion = versionService.update(dto, 1L);
        assertEquals("Version1", updatedVersion.getName());
        assertEquals(0.5F, updatedVersion.getPercentOfSpendBudget());
    }

    @Test
    void testUpdate_VersionDoesntExist() {
        VersionDto dto = new VersionDto(0.5F, "Version1");
        when(versionRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> {
            versionService.update(dto, 1L);
        });
        assertEquals("Version doesn't exist", exception.getMessage());
    }
}
