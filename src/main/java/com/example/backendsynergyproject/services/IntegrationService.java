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
    public Integration add(Integration integrationBody) throws Exception {
        if(integrationRepository.existsByName(integrationBody.getName())){
            throw new Exception("Integracja Istnieje");
        }
        else {
            return integrationRepository.save(integrationBody);
        }
    }


    @Transactional
    public Integration update(Integration integrationBody,Long id) throws Exception{
       Optional<Integration> existingIntegration = integrationRepository.findById(id);
       if(existingIntegration.isEmpty()){
           throw new Exception("W bazie nie ma takiej Integracji!");
        }
       else{
           Integration integration = existingIntegration.get();
           List<Version> versionList =  integration.getVersionList();
           versionList.clear();
           integrationBody.getVersionList().forEach((version -> {versionList.add(version);}));
           integration.setVersionList(versionList);
           integration.setName(integrationBody.getName());
           integration.setBudget(integrationBody.getBudget());
           integration.setPhoto(integrationBody.getPhoto());
           integration.setNoOfMembers(integrationBody.getNoOfMembers());
           return integrationRepository.save(integration);
       }
    }


//    public Integration mapToIntegration(IntegrationDto integrationDto){
//        Integration integration = new Integration();
//        integration.setName(integrationDto.name());
//        integration.setPhoto(integrationDto.photo());
//        integration.setBudget(integrationDto.budget());
//        integration.setNoOfMembers(integrationDto.noOfMembers());
//        integration.setVersionList(integrationDto.versionList());
//        return integration;
//    }
//
//    public IntegrationDto mapToDto(Integration integration){
//        return new IntegrationDto(integration.getName(),
//                integration.getPhoto(),
//                integration.getBudget(),
//                integration.getNoOfMembers(),
//                integration.getVersionList());
//    }
}
