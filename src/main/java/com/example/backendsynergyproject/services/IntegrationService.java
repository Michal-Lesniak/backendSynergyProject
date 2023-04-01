package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.IntegrationDto;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.repositories.IntegrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntegrationService {

    @Autowired
    private IntegrationRepository integrationRepository;

    public List<Integration> findAll(){
        return integrationRepository.findAll();
    }

    public Integration findOne(String name) {
        Optional<Integration> integration = integrationRepository.findByName(name);
        return integration.orElse(null);
    }

    public Integration findOne(Long id){
        Optional<Integration> integration = integrationRepository.findById(id);
        return integration.orElse(null);
    }

    @Transactional
    public boolean delete(Long id){
        if(integrationRepository.existsById(id)) {
            integrationRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public Integration add(IntegrationDto integrationDtoBody) throws Exception {
        if(integrationRepository.existsByName(integrationDtoBody.name())){
            throw new Exception("Integracja Istnieje");
        }
        else{
            return integrationRepository.save(mapToIntegration(integrationDtoBody));
        }
    }


    @Transactional
    public Integration update(IntegrationDto integrationDtoBody,Long id) throws Exception{
       Optional<Integration> existingIntegration = integrationRepository.findById(id);
       if(existingIntegration.isEmpty()){
           throw new Exception("W bazie nie ma takiej Integracji!");
        }
       else{
           Integration integration = existingIntegration.get();

           integration.setName(integrationDtoBody.name());
           integration.setBudget(integrationDtoBody.budget());
           integration.setPhoto(integrationDtoBody.photo());
           integration.setNoOfMembers(integrationDtoBody.noOfMembers());
           return integrationRepository.save(integration);
       }
    }


    public Integration mapToIntegration(IntegrationDto integrationDto){
        Integration integration = new Integration();
        integration.setName(integrationDto.name());
        integration.setPhoto(integrationDto.photo());
        integration.setBudget(integrationDto.budget());
        integration.setNoOfMembers(integrationDto.noOfMembers());
        return integration;
    }

    public IntegrationDto mapToDto(Integration integration){
        return new IntegrationDto(integration.getName(),
                integration.getPhoto(),
                integration.getBudget(),
                integration.getNoOfMembers());
    }
}
