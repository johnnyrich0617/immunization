package com.jrichardson.immunization.services;

import com.jrichardson.immunization.entities.VaccineType;
import com.jrichardson.immunization.repositories.VaccineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LookupService {

    @Autowired
    VaccineTypeRepository vtr;

    public List<VaccineType> getAllVaccineTypes() throws Exception{
        return vtr.findAll();
    }

    public Optional<VaccineType> getVaccineTypeById(Long vaccineTypeId) throws Exception{
        if(vtr.existsById(vaccineTypeId))
            return vtr.findById(vaccineTypeId);
        else
            throw new Exception("Requested Vaccine Type Does Not Exist");
    }
}
