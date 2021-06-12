package com.jrichardson.immunization.repositories;

import com.jrichardson.immunization.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    @Query(value = "Select citizen From Citizen citizen where citizen.ssn = :serialNum")
    Citizen validateCitizen(String serialNum);
}
