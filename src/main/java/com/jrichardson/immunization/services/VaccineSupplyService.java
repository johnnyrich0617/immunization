package com.jrichardson.immunization.services;

import com.jrichardson.immunization.entities.VaccineSupplySchedule;
import com.jrichardson.immunization.repositories.VaccineSupplyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineSupplyService {

    @Autowired
    VaccineSupplyScheduleRepository vssr;


    public List<VaccineSupplySchedule> getVaccineSchedules() throws Exception {
        return vssr.findAll();
    }

    public Optional<VaccineSupplySchedule> getScheduleForId(Long vaccineScheduleId) throws Exception {
        if(vssr.existsById(vaccineScheduleId))
            return vssr.findById(vaccineScheduleId);
        else
            throw new Exception("Requested Vaccine Schedule could not be found");
    }

    public List<VaccineSupplySchedule> getScheduleForVaccine(Long vaccineId) throws Exception {
        return vssr.getVaccineScheduleByVaccine(vaccineId);
    }

    public List<VaccineSupplySchedule> getScheduleForLocation(Long locationId) throws Exception {
        return vssr.getVaccineSchedulesByLocation(locationId);
    }

}
