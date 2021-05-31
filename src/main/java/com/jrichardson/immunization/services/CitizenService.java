package com.jrichardson.immunization.services;

import com.jrichardson.immunization.config.response.SuccessResponse;
import com.jrichardson.immunization.entities.*;
import com.jrichardson.immunization.models.AppointmentUpdateRequest;
import com.jrichardson.immunization.repositories.AppointmentRequestRepository;
import com.jrichardson.immunization.repositories.CitizenRepository;
import com.jrichardson.immunization.repositories.VaccinationAppointmentRepository;
import com.jrichardson.immunization.repositories.VaccineSupplyScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    private final static Logger logger = LoggerFactory.getLogger(CitizenService.class);

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

    public SuccessResponse<AppointmentRequestEntity> addAppointment(AppointmentRequestEntity appointment){
        Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
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

    public SuccessResponse<AppointmentUpdateRequest> updateAppointment(AppointmentUpdateRequest appointmentUpdate) {
        logger.info("updateAppoint has been invoked by API Controller............ ");
        /*
        Business Logic
        The only fields that you can update on an existing is the;
            1. VaccineData which is the type and location for the vaccination
            2. Appointment date
            3. Completed field to update completion of an appointment
         */
        Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
        try {
            validateAppointmentUpdate(appointmentUpdate);
            logger.info("appointUpdate values have been validated............ ");
            /*
            1st Get the current appointment on record
            /*
            This is just metadata
             */
            Optional<AppointmentRequestEntity> curApptMetadataOpt = vacAptRequestRepo.
                    findById(appointmentUpdate.getAppointmentId());

            /*
            This is the full appointment
             */
            Optional<VaccinationAppointment> currentAppointmentEntityOpt = vacAppointRepo.
                    findById(appointmentUpdate.getAppointmentId());

            if(curApptMetadataOpt.isEmpty()){
                logger.error("appointmentUpdate Error:: Unable to retrieve Current Appoint on Record............ ");
                throw new Exception("Unable to retrieve Current Appoint on Record");
            }

            int curApptDoseNumber = curApptMetadataOpt.get().getDoseNumber();
            VaccineType curVaccineType = currentAppointmentEntityOpt.get().getVac_data().getVaccine();
            AppointmentRequestEntity curAppointment = curApptMetadataOpt.get();

            /*
            Is there an update for the Vaccine Data,
            which is the vaccine, location, and availability date
             */
            if(appointmentUpdate.getVaccineDataId() != null){
                logger.info("appointUpdate:: Updating the Vaccination Data for this appointment............ ");
                /*
                We are updating the Vaccine Data for tis record
                 */
                /*
                Throw an Exception if there is a Vaccine data update
                and it is the same of the one currently on record
                or if the requested vaccine data update does not exist.
                 */
                if(((curApptMetadataOpt.isPresent())
                    && (!appointmentUpdate.getVaccineDataId().equals(curApptMetadataOpt.get().getVaccinationDataId()))
                    && (vssr.existsById(appointmentUpdate.getVaccineDataId())))){

                    Optional<VaccineSupplySchedule> newVaccineData = vssr.findById(appointmentUpdate.getVaccineDataId());
                    VaccineType newVacType = newVaccineData.get().getVaccine();
                    int newDesDoseNumber = newVaccineData.get().getDes_dose();
                    Date currentApptDate = curApptMetadataOpt.get().getAppointmentDate();
                    Date newVacAvailDate = newVaccineData.get().getAvail_date();

                    if(curApptDoseNumber != newDesDoseNumber || !curVaccineType.getCn().equals(newVacType.getCn())){
                        logger.error("appointmentUpdate:: Updating the Vaccination Data " +
                                "... failed..throwing Exception " +
                                "Unacceptable Vaccine Dose Number or VaccineType, Mismatch for new Vaccine Data Update");
                        throw new Exception("Unacceptable Vaccine Dose Number or " +
                                "VaccineType, Mismatch for new Vaccine Data Update");
                    }

                    /*
                    Check to see if there is a date update or
                    if the current appointment date on record is still valid for the update vaccine info.
                     */
                    if(appointmentUpdate.getAppointmentDate() != null){
                        logger.info("appointUpdate:: Updating the Vaccination Data " +
                                "for this appointment.....with a new Appointment Date....... ");
                        Date updateAppointmentDate = appointmentUpdate.getAppointmentDate();
                        /*
                        We are also updating the the Appointment Date as well as the Vaccine Data Record.
                        We must validate this date with the new Vaccine Data Record.
                         */
                        if(updateAppointmentDate.compareTo(newVacAvailDate) >= 0){
                            curAppointment.setAppointmentDate(updateAppointmentDate);
                            curAppointment.setVaccinationDataId(newVaccineData.get().getId());
                            vacAptRequestRepo.save(curAppointment);
                            logger.info("appointmentUpdate:: Updating the Vaccination Data " +
                                    "...With new Appointment Date " +
                                    "...saved update to data base...");
                        } else {
                            logger.error("appointmentUpdate:: Updating the Vaccination Data " +
                                    "...With new Appointment Date failed..throwing Exception");
                            throw new Exception("New Appointment Date is not valid for " +
                                    "the new Vaccination Data requested, Update failed...");
                        }
                    } else {
                        logger.info("appointmentUpdate:: Updating the Vaccination Data for this appointment" +
                                "..No new Appointment Date provided" +
                                "....Using current record Appointment Date........ ");
                        /*
                        We dont have new a new Appointment Date, so the original date is still being used so
                        we must validate that the original date fits within the available date of the new
                        Vaccine Data Record.
                         */
                        if(newVacAvailDate.after(currentApptDate)){
                            logger.error("appointUpdate:: Updating the Vaccination Data " +
                                    "...Without new Appointment Date failed..throwing Exception");
                            throw new Exception("Unable to Update Appointment: " +
                                    "Vaccine Availability Date must be before appointment date");
                        }
                        /*
                        Looks Good so lets..
                        Set and Update the new Vaccine Data
                         */
                        curAppointment.setVaccinationDataId(newVaccineData.get().getId());
                        vacAptRequestRepo.save(curAppointment);
                        logger.info("appointUpdate:: Updating the Vaccination Data " +
                                "...Without new Appointment Date " +
                                "...saved update to data base...");
                    }

                }else{
                    logger.error("appointUpdate:: Updating the Vaccination Data ]" +
                            "for this appointment......Exception Thrown...... ");
                    throw new Exception("Unable to update Vaccine Appointment; " +
                            "Trying to update the same Vaccine Data for this Appointment " +
                            "or the new Vaccine Data does not exist");
                }
                //End Vaccine Data Check
            } else if(appointmentUpdate.getAppointmentDate() != null){
                logger.info("appointmentUpdate:: Updating the Vaccination Date only for this appointment............ ");
                /*
                Are we only updating the appointment date,
                 */
                Date currentApptDate = curApptMetadataOpt.get().getAppointmentDate();
                Date newApptDate = appointmentUpdate.getAppointmentDate();
                if(currentApptDate.compareTo(newApptDate) == 0){
                    logger.error("appointmentUpdate:: Updating the Vaccination Date only for this appointment" +
                            "....Failed throwing Exception for new Date validity........ ");
                    throw new Exception("There was a new Appointment Date provided, " +
                            "however this Date is the same as the Appointment Date on Record");
                }
                Optional<VaccineSupplySchedule> curVaccineData = vssr.findById(curApptMetadataOpt
                                                                                .get()
                                                                                .getVaccinationDataId());
                if(curVaccineData.isPresent()){
                    Date vaccineAvailDate = curVaccineData.get().getAvail_date();
                    if(newApptDate.after(vaccineAvailDate)){
                        curAppointment.setAppointmentDate(newApptDate);
                        vacAptRequestRepo.save(curAppointment);
                        logger.info("appointmentUpdate:: Updating the new Vaccination Appointment Date " +
                                    "...saved update to data base...");
                    } else {
                        logger.error("appointmentUpdate:: Updating the Vaccination Date only for this appointment" +
                                "....Failed .... throwing Exception for new Date validity " +
                                "on Vaccine Availability........ ");
                        throw new Exception("New Appointment Date is before the Availability Date for the vaccine");
                    }
                } else {
                    logger.error("appointmentUpdate:: Updating the Vaccination Date only for this appointment" +
                            "....Failed ,,,,throwing Exception " +
                            "... Unable to retrieve current Vaccination Data For request........ ");
                    throw new Exception("Unable to retrieve current Vaccination Data For request");
                }
            //End Updating Date
            } else if(appointmentUpdate.isCompleted() != null){
                logger.info("appointmentUpdate:: Updating the Completed Flag Only ............ ");
                /*
                We are updating the completed flag
                 */
                if(!appointmentUpdate.isCompleted()){
                    logger.error("appointmentUpdate:: Updating the Completed Flag only for this appointment" +
                            "....Failed ....throwing Exception " +
                            "... The current Appointment on Record is already in a state of Not Completed........ ");
                    throw new Exception("The current Appointment on Record is " +
                            "already in a state of Not Completed, Unable to update completed flag");
                }
                Date currentApptDate = curApptMetadataOpt.get().getAppointmentDate();
                /*
                Has the Appointment occurred yet?
                 */
                if(currentApptDate.compareTo(transactionDate) >= 0 ){
                    /*
                    Update the flag
                     */
                    curAppointment.setCompleted(appointmentUpdate.isCompleted().booleanValue());
                    vacAptRequestRepo.save(curAppointment);
                    logger.info("appointmentUpdate:: Updating the new Completed Flag " +
                            "...saved update to data base...");
                } else {
                    /*
                    Do not update the flag the appointment has not occurred
                     */
                    logger.error("appointmentUpdate:: Updating the Completed Flag only for this appointment" +
                            "....Failed ....throwing Exception " +
                            "... The Current Appointment of record has not occurred yet........ ");
                   throw new Exception("The Current Appointment of record has not occurred yet," +
                           " unable to update completed flag for request");
                }
            }
            /*
            The update succeeded....
             */
            return new SuccessResponse<>(transactionDate, "Successfully Updated Appointment",
                    true, HttpStatus.CREATED, appointmentUpdate);
        } catch (Exception e){
            /*
            There has been an exception....The Update failed....
             */
            return new SuccessResponse<>(transactionDate, e.getMessage(),
                    false, HttpStatus.UNPROCESSABLE_ENTITY, appointmentUpdate);
        }
    }

    private void validateAppointmentUpdate(AppointmentUpdateRequest updateRequest)throws Exception{

        /*
        Validate the contents of the Vaccine Update Request.
        There must be a valid AppointmentId and a valid CitizenId
         */
        if(updateRequest.getAppointmentId() == null){
            logger.error("validateAppointmentUpdate failed.." +
                    "...AppointmentId is required to update a Vaccination Appointment");
            throw new Exception("AppointmentId is required to update a Vaccination Appointment");
        }
        if(updateRequest.getCitizenId() == null){
            logger.error("validateAppointmentUpdate failed.." +
                    "...CitizenId is required to update a Vaccination Appointment");
            throw new Exception("CitizenId is required to update a Vaccination Appointment");
        }
        if(!vacAppointRepo.existsById(updateRequest.getAppointmentId())){
            logger.error("validateAppointmentUpdate failed.." +
                    "...No Appointment found for request");
            throw new Exception("No Appointment found for request, " +
                    "Unable to find existing Appointment for ID of: "
                    + updateRequest.getAppointmentId());
        } else {
            /*
            Check to ensure that the current appoint of record has not been completed.
            Check to ensure the update request is for the same citizen for the appointment of record.
             */
            Optional<VaccinationAppointment> curAppointment = vacAppointRepo.findById(updateRequest.getAppointmentId());
            if(curAppointment.get().isCompleted()){
                logger.error("validateAppointmentUpdate failed.." +
                        "...Unable to update Appointment, Current Appointment has been completed");
                throw new Exception("Unable to update Appointment, Current Appointment has been completed");
            }
            if(curAppointment.get().getCitizen().getId() != updateRequest.getCitizenId()){
                logger.error("validateAppointmentUpdate failed.." +
                        "...Appointment Update Request CitizenId " +
                        "does not match current Vaccine Appointment on record.");
                throw new Exception("Appointment Update Request CitizenId does not " +
                        "match current Vaccine Appointment on record.  " +
                        "Unable to complete request.");
            }
        }
    }

    private void validateAppointment( AppointmentRequestEntity appointment) throws Exception{

        /*
        Check to ensure there is a CitizenId for this request and the Citizen exists in the database,
        if not throw exception
         */
        if(appointment.getCitizenId() == null || !cr.existsById(appointment.getCitizenId())){
            logger.error("validateAppointment failed.." +
                    "...A valid CitizenId is required for this transaction");
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
            logger.error("validateAppointment failed.." +
                    "...A valid Dose Number is required for this transaction");
            throw new Exception("A valid Dose Number is required for this transaction, " +
                    "Dose Number must be a value of 1 or 2");
        }

        if(appointment.getDoseNumber() == 1){
            List<VaccinationAppointment> appointments = vacAppointRepo
                    .findAppointmentsByCitizenIdForDose(appointment.getCitizenId(), 1);
            if(appointments.size() > 0){
                logger.error("validateAppointment failed.." +
                        "...Illegal Request::The Citizen already has an appointment for a 1st Dose Vaccination");
                throw new Exception("Illegal Request::The Citizen already " +
                        "has an appointment for a 1st Dose Vaccination");
            }

        }else if(appointment.getDoseNumber() == 2){

            List<VaccinationAppointment> firstDoseAppointments = vacAppointRepo
                    .findAppointmentsByCitizenIdForDose(appointment.getCitizenId(), 1);

            if(firstDoseAppointments.size() > 1){
                logger.error("validateAppointment failed.." +
                        "...Illegal Request:: This citizen has too many appointments");
                throw new Exception("Illegal Request::This citizen has too many appointments " +
                        "(completed or not complete), No further requests will be granted or accepted," +
                        " please contact the Dept. of Health for further action");
            }

            if(firstDoseAppointments.size() == 0 ){
                logger.error("validateAppointment failed.." +
                        "...Illegal Request:: A first Dose Vaccination is required prior to Second Dose request");
                throw new Exception("A first Dose Vaccination is required prior to Second Dose request");
            }

            if(firstDoseAppointments.size() == 1 &&
                    firstDoseAppointments.get(0).getVac_data()
                            .getVaccine().getCn().equals("Johnson & Johnson’s Janssen")){
                logger.error("validateAppointment failed.." +
                        "...The Johnson & Johnson’s Janssen vaccine " +
                        "only requires a single dose for full vaccination.");

                throw new Exception("The Johnson & Johnson’s Janssen vaccine" +
                        " only requires a single dose for full vaccination.");

            }

            Optional<VaccineSupplySchedule> requestVacData = vssr.findById(appointment.getVaccinationDataId());

            VaccinationAppointment firstDose = firstDoseAppointments.get(0);
            VaccineSupplySchedule vacData = firstDose.getVac_data();
            VaccineType vacType = vacData.getVaccine();
            if(requestVacData.isEmpty() || !vacType.getCn().equalsIgnoreCase(requestVacData.get().getVaccine().getCn())){
                logger.error("validateAppointment failed.." +
                        "... Requested 2nd Dose Vaccine does not match 1st Dose Vaccine Type");
                throw new Exception("Requested 2nd Dose Vaccine does not match 1st Dose Vaccine Type,  " +
                        "For 2nd Dose Vaccinations the 1st Dose and 2nd Dose must be the same type.  " +
                        "In the case of Johnson and Johnson there can only be a 1st Dose vaccination");
            }

            List<VaccinationAppointment> secondDoseAppointments = vacAppointRepo
                    .findAppointmentsByCitizenIdForDose(appointment.getCitizenId(), 2);

            if(secondDoseAppointments.size() > 0){
                logger.error("validateAppointment failed.." +
                        "... Illegal Request::The Citizen already has an appointment for a 2nd Dose Vaccination");
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
            logger.error("validateAppointment failed.." +
                    "... Requested Appointment Date must be after the Vaccine Availability Date");
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
