package com.airline.repository;

import com.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE " +
           "f.departureAirport = :departure AND " +
           "f.arrivalAirport = :arrival AND " +
           "DATE(f.departureTime) = DATE(:date)")
    List<Flight> searchFlights(
        @Param("departure") String departure,
        @Param("arrival") String arrival,
        @Param("date") LocalDateTime date);
    
    List<Flight> findByStatus(FlightStatus status);
}