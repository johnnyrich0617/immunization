package com.jrichardson.immunization.repositories;

import com.jrichardson.immunization.entities.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccineType, Long> {
}
