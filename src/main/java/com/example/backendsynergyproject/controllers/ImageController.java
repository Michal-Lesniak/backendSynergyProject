package com.example.backendsynergyproject.controllers;

import com.example.backendsynergyproject.services.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageDataService imageDataService;

    @GetMapping( "/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id){
        byte[] imageData=imageDataService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable Long id){
        if(imageDataService.deleteImage(id)){
            return ResponseEntity.ok().body(true);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
