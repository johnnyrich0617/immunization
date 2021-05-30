package com.jrichardson.immunization.repositories;

import com.jrichardson.immunization.entities.AppointmentRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequestEntity, Long> {
}
