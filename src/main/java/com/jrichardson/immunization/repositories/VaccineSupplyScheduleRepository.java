package com.jrichardson.immunization.repositories;


import com.jrichardson.immunization.entities.VaccineSupplySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineSupplyScheduleRepository extends JpaRepository<VaccineSupplySchedule, Long> {

    @Query(value = "Select vs From VaccineSupplySchedule vs where vs.vaccine.id = :id")
    List<VaccineSupplySchedule> getVaccineScheduleByVaccine(Long id);

    @Query(value = "Select vs From VaccineSupplySchedule vs where vs.location.id = :id")
    List<VaccineSupplySchedule> getVaccineSchedulesByLocation(Long id);
}
