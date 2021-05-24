package com.jrichardson.immunization.repositories;

import com.jrichardson.immunization.entities.VaccinationAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationAppointmentRepository extends JpaRepository<VaccinationAppointment, Long> {
}
