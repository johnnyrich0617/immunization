package com.jrichardson.immunization.services;

import com.jrichardson.immunization.config.response.SuccessResponse;
import com.jrichardson.immunization.entities.*;
import com.jrichardson.immunization.repositories.AppointmentRequestRepository;
import com.jrichardson.immunization.repositories.CitizenRepository;
import com.jrichardson.immunization.repositories.VaccinationAppointmentRepository;
import com.jrichardson.immunization.repositories.VaccineSupplyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository cr;

    @Autowired
    AppointmentRequestRepository vacAptRequestRepo;

    @Autowired
    VaccinationAppointmentRepository vacAppointRepo;

    @Autowired
    VaccineSupplyScheduleRepository vssr;


    public Optional<Citizen> getCitizen(Long citizenId) throws Exception {
        if(cr.existsById(citizenId))
            return cr.findById(citizenId);
        else
            throw new Exception("Requested Citizen does not exist");
    }

    public List<Citizen> getCitizens() throws Exception {
        return cr.findAll();
    }

    public SuccessResponse addAppointment(AppointmentRequestEntity appointment){
        Date transactionDate = new Date(System.currentTimeMillis());
        try {
            validateAppointment(appointment);
            vacAptRequestRepo.save(appointment);
            return new SuccessResponse<>(transactionDate, "Successfully Created New Appointment",
                    true, HttpStatus.CREATED, appointment);
        }catch(Exception e){
            return new SuccessResponse<>(transactionDate, e.getMessage(),
                    false, HttpStatus.UNPROCESSABLE_ENTITY, appointment);
        }
    }

    private void validateAppointment( AppointmentRequestEntity appointment) throws Exception{

        /*
        Check to ensure there is a CitizenId for this request and the Citizen exists in the database,
        if not throw exception
         */
        if(appointment.getCitizenId() == null || !cr.existsById(appointment.getCitizenId())){
            throw new Exception("A valid CitizenId is required for this transaction");
        }

        /*
        Check for dose number valid values (1 or 2), if not throw exception
        if the dose number is 1, ensure that there is not a 1st does:
            a. scheduled and has not been completed, if so throw exception
        If the dose number is 2, ensure that there is a a corresponding
        1st dose which meets the following criteria
            a. The Vaccine type for the 1st does matches the second dose
            b. and the vaccine type/name is Moderna or Pfizer-BioNTech.
            c. or that there is a 1st dose that has been completed and the vaccine type is Johnson & Johnson’s Janssen
            if so throw exception
         */
        if(!isDoseValidValue(appointment.getDoseNumber())){
            throw new Exception("A valid Dose Number is required for this transaction, " +
                    "Dose Number must be a value of 1 or 2");
        }

        if(appointment.getDoseNumber() == 1){
            List<VaccinationAppointment> appointments = vacAppointRepo
                    .findAppointmentsByCitizenIdForDose(appointment.getCitizenId(), 1);
            if(appointments.size() > 0){
                throw new Exception("Illegal Request::The Citizen already " +
                        "has an appointment for a 1st Dose Vaccination");
            }

        }else if(appointment.getDoseNumber() == 2){

            List<VaccinationAppointment> firstDoseAppointments = vacAppointRepo
                    .findAppointmentsByCitizenIdForDose(appointment.getCitizenId(), 1);

            if(firstDoseAppointments.size() > 1){
                throw new Exception("Illegal Request::This citizen has too many appointments " +
                        "(completed or not complete), No further requests will be granted or accepted," +
                        " please contact the Dept. of Health for further action");
            }

            if(firstDoseAppointments.size() == 0 ){
                throw new Exception("A first Dose Vaccination is required prior to Second Dose request");
            }

            if(firstDoseAppointments.size() == 1 &&
                    firstDoseAppointments.get(0).getVac_data()
                            .getVaccine().getCn().equals("Johnson & Johnson’s Janssen")){
                throw new Exception("The Johnson & Johnson’s Janssen vaccine" +
                        " only requires a single dose for full vaccination.");

            }

            Optional<VaccineSupplySchedule> requestVacData = vssr.findById(appointment.getVaccinationDataId());

            VaccinationAppointment firstDose = firstDoseAppointments.get(0);
            VaccineSupplySchedule vacData = firstDose.getVac_data();
            VaccineType vacType = vacData.getVaccine();
            if(requestVacData.isEmpty() || !vacType.getCn().equalsIgnoreCase(requestVacData.get().getVaccine().getCn())){
                throw new Exception("Requested 2nd Dose Vaccine does not match 1st Dose Vaccine Type,  " +
                        "For 2nd Dose Vaccinations the 1st Dose and 2nd Dose must be the same type.  " +
                        "In the case of Johnson and Johnson there can only be a 1st Dose vaccination");
            }

            List<VaccinationAppointment> secondDoseAppointments = vacAppointRepo
                    .findAppointmentsByCitizenIdForDose(appointment.getCitizenId(), 2);

            if(secondDoseAppointments.size() > 0){
                throw new Exception("Illegal Request::The Citizen already " +
                        "has an appointment for a 2nd Dose Vaccination");
            }
        }
        /*
        Check to ensure that the requested Vaccination Appointment Date is after the availability of the vaccine
         */
        Date requestedApptDate = appointment.getAppointmentDate();
        Optional<VaccineSupplySchedule> requestVacData = vssr.findById(appointment.getVaccinationDataId());
        Date vacAvaildate = requestVacData.get().getAvail_date();
        if(requestedApptDate.before(vacAvaildate)){
            throw new Exception("Requested Appointment Date must be after the Vaccine Availability Date");
        }

    }

    private boolean isDoseValidValue(int doseNumber){
        if(doseNumber == 1){
            return true;
        }else if(doseNumber == 2){
            return true;
        }
        else{
            return false;
        }
    }
}
