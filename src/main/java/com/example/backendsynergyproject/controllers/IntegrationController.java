package com.example.backendsynergyproject.controllers;


import com.example.backendsynergyproject.dto.IntegrationDto;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.services.IntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("integrations")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping
    public ResponseEntity<List<Integration>> GetIntegrations() {
        return ResponseEntity.ok().body(integrationService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Integration> GetOneIntegretioan(@PathVariable Long id){
        return ResponseEntity.ok().body(integrationService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Integration> AddIntegration(@RequestBody Integration integrationBody) {
        try {
            Integration integration = integrationService.addIntegration(integrationBody);
            return ResponseEntity.ok().body(integration);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteIntegration(@PathVariable Long id) {
        if (integrationService.deleteIntegration(id)) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integration> updateIntegration(@PathVariable Long id, @RequestBody Integration updatedintegration){
        try {
            Integration changedintegration = integrationService.changeIntegration(updatedintegration, id);
            return ResponseEntity.ok().body(changedintegration);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


//    ToDo:
//      GetMapping - get One Integration
}
