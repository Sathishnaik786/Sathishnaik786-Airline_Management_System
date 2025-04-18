package com.airline.service.impl;

import com.airline.dto.BookingDTO;
import com.airline.exception.ResourceNotFoundException;
import com.airline.model.*;
import com.airline.repository.BookingRepository;
import com.airline.repository.FlightRepository;
import com.airline.repository.PassengerRepository;
import com.airline.service.BookingService;
import com.airline.util.BookingReferenceGenerator;
import com.airline.util.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final BookingMapper bookingMapper;
    private final BookingReferenceGenerator referenceGenerator;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Flight flight = flightRepository.findById(bookingDTO.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Flight", "id", bookingDTO.getFlightId()));
        
        Passenger passenger = passengerRepository.findById(bookingDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Passenger", "id", bookingDTO.getPassengerId()));
        
        if (bookingRepository.existsBySeatNumberAndFlightId(
                bookingDTO.getSeatNumber(), bookingDTO.getFlightId())) {
            throw new IllegalStateException("Seat is already booked");
        }
        
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setBookingReference(referenceGenerator.generate());
        
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDTO(savedBooking);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        return bookingMapper.toDTO(booking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        
        bookingMapper.updateEntity(bookingDTO, existingBooking);
        Booking updatedBooking = bookingRepository.save(existingBooking);
        return bookingMapper.toDTO(updatedBooking);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDTO> getBookingsByUser(Long userId) {
        return bookingRepository.findByPassengerUserId(userId).stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }
}