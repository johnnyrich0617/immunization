package com.jrichardson.immunization.repositories;

import com.jrichardson.immunization.entities.ImmunizationLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<ImmunizationLocation, Long> {
}
