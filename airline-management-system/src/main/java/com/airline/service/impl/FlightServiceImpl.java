package com.airline.service.impl;

import com.airline.dto.FlightDTO;
import com.airline.exception.ResourceNotFoundException;
import com.airline.model.Aircraft;
import com.airline.model.Flight;
import com.airline.repository.AircraftRepository;
import com.airline.repository.FlightRepository;
import com.airline.service.FlightService;
import com.airline.util.mapper.FlightMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final FlightMapper flightMapper;

    @Override
    @Transactional
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Aircraft aircraft = aircraftRepository.findById(flightDTO.getAircraftId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aircraft", "id", flightDTO.getAircraftId()));
        
        Flight flight = flightMapper.toEntity(flightDTO);
        flight.setAircraft(aircraft);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDTO(savedFlight);
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "id", id));
        return flightMapper.toDTO(flight);
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "id", id));
        
        Aircraft aircraft = aircraftRepository.findById(flightDTO.getAircraftId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aircraft", "id", flightDTO.getAircraftId()));
        
        flightMapper.updateEntity(flightDTO, existingFlight);
        existingFlight.setAircraft(aircraft);
        Flight updatedFlight = flightRepository.save(existingFlight);
        return flightMapper.toDTO(updatedFlight);
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "id", id));
        flightRepository.delete(flight);
    }

    @Override
    public List<FlightDTO> searchFlights(String departure, String arrival, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.parse(date + "T00:00:00");
        
        return flightRepository.searchFlights(departure, arrival, dateTime).stream()
                .map(flightMapper::toDTO)
                .collect(Collectors.toList());
    }
}