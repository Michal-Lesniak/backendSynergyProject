package com.example.backendsynergyproject.services;

import com.example.backendsynergyproject.dto.VersionDto;
import com.example.backendsynergyproject.models.Version;
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

    public List<Version> findAll(){
        List<Version> versionList = versionRepository.findAll();
        return versionList;
    }

    public Version findOne(Long id){
        Optional<Version> version =  versionRepository.findById(id);
        return version.orElse(null);
    }

    @Transactional
    public Version addVersion(Version version) throws Exception{
            return versionRepository.save(version);
            //ToDo Add Validation
    }

    public VersionDto mapToDto(Version version){
       return new VersionDto(
                version.getPercentOfSpendBudget(),
                version.getName()
        );
    }

    public Version mapToVersion(VersionDto versionDto){
        Version version = new Version();
        version.setPercentOfSpendBudget(versionDto.percentOfSpendBudget());
        version.setName(versionDto.name());
        return version;
    }

}
