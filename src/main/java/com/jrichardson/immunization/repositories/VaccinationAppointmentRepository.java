package com.jrichardson.immunization.repositories;

import com.jrichardson.immunization.entities.VaccinationAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationAppointmentRepository extends JpaRepository<VaccinationAppointment, Long> {

   @Query("Select appointment from VaccinationAppointment appointment where " +
           "appointment.citizen.id = :citizenId AND appointment.sched_dose_num = :doseNumber")
    List<VaccinationAppointment> findAppointmentsByCitizenIdForDose(@Param("citizenId") Long citizenId,
                                                                    @Param("doseNumber") int doseNumber);
}
