package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.models.ImageData;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.repositories.ImageDataRepository;
import com.example.backendsynergyproject.repositories.IntegrationRepository;
import com.example.backendsynergyproject.utils.ImageDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    ImageDataRepository imageDataRepository;
    @Autowired
    IntegrationRepository integrationRepository;

    public ImageData uploadImage(MultipartFile file, Long id) throws Exception {
        if(integrationRepository.existsById(id)) {
            Integration integration =  integrationRepository.findById(id).get();
            return imageDataRepository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageDataUtils.compressImage(file.getBytes()))
                    .integration(integration).build());
        }
        else {
            throw new Exception("Integration Not Found");
        }
    }

    public byte[] downloadImage(Long id){
        Optional<ImageData> dbImageData = imageDataRepository.findById(id);
        byte[] images=ImageDataUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public Boolean deleteImage(Long id){
        if(imageDataRepository.existsById(id)) {
            imageDataRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
