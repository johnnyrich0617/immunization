package com.jrichardson.immunization.services;

import com.jrichardson.immunization.entities.Citizen;
import com.jrichardson.immunization.repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository cr;


    public Optional<Citizen> getCitizen(Long citizenId) throws Exception {
        if(cr.existsById(citizenId))
            return cr.findById(citizenId);
        else
            throw new Exception("Requested Citizen does not exist");
    }

    public List<Citizen> getCitizens() throws Exception {
        return cr.findAll();
    }
}
