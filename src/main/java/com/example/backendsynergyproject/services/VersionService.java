package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import com.example.backendsynergyproject.repositories.IntegrationRepository;
import com.example.backendsynergyproject.repositories.VersionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersionService {

    @Autowired
    private VersionRepository versionRepository;
    @Autowired
    private IntegrationRepository integrationRepository;

    public List<Version> findAll(){
        List<Version> versionList = versionRepository.findAll();
        return versionList;
    }

    public List<Version> findAllFromIntegration(Long integration_id) throws Exception{
        if(integrationRepository.existsById(integration_id)) {
            return versionRepository.findAllByIntegration_Id(integration_id);
        }else {
            throw new Exception("Integration Not Found");
        }
    }

    public Version findOne(Long id) throws Exception{
        return versionRepository.findById(id).orElseThrow(()-> new Exception("Version Not Found"));
    }

    @Transactional
    public Integration add(Version version, Long integration_id) throws Exception{
        Optional<Integration> integration = integrationRepository.findById(integration_id);
        if(integration.isEmpty()){
            throw new Exception("Taka integracja nie istnieje!");
        }else {
            Integration integrationToUpdate = integration.get();
            integrationToUpdate.addVersion(version);
            return integrationRepository.save(integrationToUpdate);
        }

    }


    @Transactional
    public Boolean delete(Long id){
        if(versionRepository.existsById(id)){
            versionRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public Version update(Version versionBody, long id) throws Exception{
        Optional<Version> version = versionRepository.findById(id);
        if(version.isEmpty()){
            throw new Exception("Version doesn't exist");
        }else {
            Version updatedVersion = version.get();
            updatedVersion.setPercentOfSpendBudget(versionBody.getPercentOfSpendBudget());
            updatedVersion.setName(versionBody.getName());
            return updatedVersion;
        }
    }

//    public VersionDto mapToDto(Version version){
//       return new VersionDto(
//                version.getPercentOfSpendBudget(),
//                version.getName()
//        );
//    }
//
//    public Version mapToVersion(VersionDto versionDto){
//        Version version = new Version();
//        version.setPercentOfSpendBudget(versionDto.percentOfSpendBudget());
//        version.setName(versionDto.name());
//        return version;
//    }

}
