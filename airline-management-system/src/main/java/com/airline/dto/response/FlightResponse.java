package com.airline.dto.response;

import com.airline.model.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private Long id;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long aircraftId;
    private String aircraftModel;
    private Integer aircraftCapacity;
    private Double basePrice;
    private FlightStatus status;
    private Integer availableSeats;
}