package com.airline.repository;

import com.airline.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    boolean existsByRegistrationNumber(String registrationNumber);
}