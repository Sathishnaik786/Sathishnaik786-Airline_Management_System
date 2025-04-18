package com.airline.repository;

import com.airline.model.Booking;
import com.airline.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPassenger(Passenger passenger);
    List<Booking> findByBookingStatus(BookingStatus status);
    boolean existsBySeatNumberAndFlightId(String seatNumber, Long flightId);
}