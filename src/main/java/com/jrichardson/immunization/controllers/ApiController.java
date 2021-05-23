package com.jrichardson.immunization.controllers;


import com.jrichardson.immunization.config.exceptions.IMAEntityRequestException;
import com.jrichardson.immunization.config.exceptions.IMAInvalidRequestException;
import com.jrichardson.immunization.entities.Citizen;
import com.jrichardson.immunization.entities.ImmunizationLocation;
import com.jrichardson.immunization.entities.VaccineSupplySchedule;
import com.jrichardson.immunization.entities.VaccineType;
import com.jrichardson.immunization.models.MapLocationData;
import com.jrichardson.immunization.services.CitizenService;
import com.jrichardson.immunization.services.LookupService;
import com.jrichardson.immunization.services.MapService;
import com.jrichardson.immunization.services.VaccineSupplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final static Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    CitizenService cs;

    @Autowired
    MapService ms;

    @Autowired
    LookupService ls;

    @Autowired
    VaccineSupplyService supplyService;

    public ApiController(){ super(); }

    @GetMapping(value = "/v1/citizens", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Citizen>> getCitizens(){
        try {
            return ResponseEntity.ok(cs.getCitizens());
        }catch(Exception e){
            throw new IMAInvalidRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/citizens/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Optional<Citizen>> getCitizensById(@PathVariable(value = "id") Long citizenId){
        try {
            return ResponseEntity.ok(cs.getCitizen(citizenId));
        } catch(Exception e){
            throw new IMAEntityRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/vaccinetypes", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<VaccineType>> getVaccineTypes(){
        try{
            return ResponseEntity.ok(ls.getAllVaccineTypes());
        } catch(Exception e){
            throw new IMAInvalidRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/vaccinetypes/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Optional<VaccineType>> getVaccineType(@PathVariable(value = "id") Long vaccineTypeId){
        try{
            return ResponseEntity.ok(ls.getVaccineTypeById(vaccineTypeId));
        } catch(Exception e){
            throw new IMAEntityRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/map", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<MapLocationData> getMapLocations(){
        try {
            return ResponseEntity.ok(ms.getMapLocations());
        }catch(Exception e){
            throw new IMAInvalidRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/map/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Optional<ImmunizationLocation>> getMapLocation(@PathVariable(value="id") Long locationId){
        try{
            return ResponseEntity.ok(ms.getLocation(locationId));
        }catch(Exception e){
            throw new IMAEntityRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/schedules", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<VaccineSupplySchedule>> getSchedulesForAll() {
        try{
            return ResponseEntity.ok(supplyService.getVaccineSchedules());
        } catch(Exception e){
            throw new IMAInvalidRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/schedules/vaccine/{vaccineId}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<VaccineSupplySchedule>> getSchedulesForVaccine(
            @PathVariable(value = "vaccineId") Long vaccineId) {
        try{
            return ResponseEntity.ok(supplyService.getScheduleForVaccine(vaccineId));
        } catch(Exception e){
            throw new IMAEntityRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/schedules/location/{locationId}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<VaccineSupplySchedule>> getSchedulesForLocation(
            @PathVariable(value = "locationId") Long locationId){
        try{
            return ResponseEntity.ok(supplyService.getScheduleForLocation(locationId));
        } catch(Exception e){
            throw new IMAEntityRequestException(e.getMessage(), e);
        }
    }
}
