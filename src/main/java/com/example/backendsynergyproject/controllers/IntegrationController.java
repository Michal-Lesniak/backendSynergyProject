package com.example.backendsynergyproject.controllers;


import com.example.backendsynergyproject.dto.IntegrationDto;
import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.ImageData;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.services.ImageDataService;
import com.example.backendsynergyproject.services.IntegrationService;
import com.example.backendsynergyproject.services.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("integration")
public class IntegrationController {

    @Autowired
    IntegrationService integrationService;
    @Autowired
    VersionService versionService;
    @Autowired
    ImageDataService imageDataService;


    @GetMapping
    public ResponseEntity<List<Integration>> getIntegrations() {
        return ResponseEntity.ok().body(integrationService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Integration> getOneIntegration(@PathVariable Long id){
        return ResponseEntity.ok().body(integrationService.findOne(id));
    }

    @GetMapping("/{integration_id}/version")
    public ResponseEntity<List<Version>> getAllVersionsFromIntegration(@PathVariable Long integration_id){
        try {
            List<Version> versionList = versionService.findAllFromIntegration(integration_id);
            return ResponseEntity.ok().body(versionList);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Integration> addIntegration(@RequestBody IntegrationDto integrationDtoBody) {
        try {
            Integration integration = integrationService.add(integrationDtoBody);
            return ResponseEntity.ok().body(integration);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/version")
    public ResponseEntity<Version> addVersion(@PathVariable Long id, @RequestBody VersionDto versionDto) {
        try {
            List<Version> versionList =  versionService.add(versionDto, id).getVersionList();
            Version addedVersion = versionList.get(versionList.size() - 1);
            return ResponseEntity.ok().body(addedVersion);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> addImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            ImageData imageData =  imageDataService.uploadImage(file, id);
            return ResponseEntity.ok().body(imageData);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteIntegration(@PathVariable Long id) {
        if (integrationService.delete(id)) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integration> updateIntegration(@PathVariable Long id, @RequestBody IntegrationDto updatedintegrationDto){
        try {
            Integration changedintegration = integrationService.update(updatedintegrationDto, id);
            return ResponseEntity.ok().body(changedintegration);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


}
