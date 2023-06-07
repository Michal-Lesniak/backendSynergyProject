package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.controllers.VersionController;
import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.services.VersionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

public class VersionControllerTest {

    @Mock
    private VersionService versionService;

    @InjectMocks
    private VersionController versionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVersions() {
        List<Version> versions = new ArrayList<>();
        versions.add(new Version());
        when(versionService.findAll()).thenReturn(versions);
        ResponseEntity<List<Version>> response = versionController.getAllVersions();
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetOneVersion() throws Exception {
        when(versionService.findOne(anyLong())).thenReturn(new Version());
        ResponseEntity<Version> response = versionController.getOneVersion(1L);
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateVersion() throws Exception {
        when(versionService.update(any(VersionDto.class), anyLong())).thenReturn(new Version());
        ResponseEntity<Version> response = versionController.updateVersion(1L, new VersionDto(50.5f, "TestVersion"));
        assertNotNull(response.getBody());
    }

    @Test
    void testGetOneVersion_ExceptionThrown() throws Exception {
        Mockito.doThrow(new Exception("Test exception")).when(versionService).findOne(anyLong());

        ResponseEntity<Version> response = versionController.getOneVersion(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Mockito.verify(versionService, Mockito.times(1)).findOne(anyLong());
    }

    @Test
    void testUpdateVersion_ExceptionThrown() throws Exception {
        Mockito.doThrow(new Exception("Test exception")).when(versionService).update(any(VersionDto.class), anyLong());

        ResponseEntity<Version> response = versionController.updateVersion(1L, new VersionDto(50.5f, "TestVersion"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Mockito.verify(versionService, Mockito.times(1)).update(any(VersionDto.class), anyLong());
    }

}
