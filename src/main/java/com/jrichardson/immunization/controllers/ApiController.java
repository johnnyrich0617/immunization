package com.jrichardson.immunization.controllers;


import com.jrichardson.immunization.config.exceptions.IMAEntityRequestException;
import com.jrichardson.immunization.config.exceptions.IMAInvalidRequestException;
import com.jrichardson.immunization.config.response.SuccessResponse;
import com.jrichardson.immunization.entities.*;
import com.jrichardson.immunization.models.AppointmentAddRequest;
import com.jrichardson.immunization.models.MapLocationData;
import com.jrichardson.immunization.services.CitizenService;
import com.jrichardson.immunization.services.LookupService;
import com.jrichardson.immunization.services.MapService;
import com.jrichardson.immunization.services.VaccineSupplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.Timestamp;
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
            logger.info("getCitizen called ....... ");
            return ResponseEntity.ok(cs.getCitizens());
        }catch(Exception e){
            logger.error("getCitizens called  ...Exception has occurred");
            logger.error("getCitizens Exception = " + e);
            throw new IMAInvalidRequestException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/v1/citizens/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Optional<Citizen>> getCitizensById(@PathVariable(value = "id") Long citizenId){
        try {
            logger.info("getCitizensById called with request Citizen " + citizenId);
            return ResponseEntity.ok(cs.getCitizen(citizenId));
        } catch(Exception e){
            logger.error("getCitizensById called with request Citizen " + citizenId + " ...Exception has occurred");
            logger.error("getCitizensById Exception = " + e);
            throw new IMAEntityRequestException(e.getMessage(), e);
        }
    }

    @PostMapping(path = "/v1/citizens/appointment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> addAppointment(@RequestBody AppointmentAddRequest appointmentRequest){
        logger.info("addAppointment called ");

        AppointmentRequestEntity appointment = new AppointmentRequestEntity();
        appointment.setCitizenId(appointmentRequest.getCitizenId());
        appointment.setVaccinationDataId(appointmentRequest.getVaccinationDataId());
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        appointment.setDoseNumber(appointmentRequest.getDoseNumber());
        appointment.setCompleted(appointmentRequest.isCompleted());

        SuccessResponse sresponse = cs.addAppointment(appointment);
        if(sresponse.getStatus() == HttpStatus.CREATED){
            logger.info("addAppointment() ...New Appointment Created for Citizen =  " +appointment.getCitizenId());
            logger.info("Appointment = " +appointment.toString() );
            return ResponseEntity.ok(sresponse);
        }else if(sresponse.getStatus() == HttpStatus.UNPROCESSABLE_ENTITY){
            logger.error("addAppointment()  ...Error has occurred "
                        + "CitizenId = " +appointment.getCitizenId() +","
                        + " Appointment Vaccine Id = " +appointment.getVaccinationDataId() +","
                        + " Appointment Vaccine Dose Number = " +appointment.getDoseNumber()+","
                        + " Appointment Date = " +appointment.getAppointmentDate() +" ....END APPOINTMENT DATA..."
            );
            return ResponseEntity.badRequest().body(sresponse);

        }
        return ResponseEntity.badRequest().body(sresponse);
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
