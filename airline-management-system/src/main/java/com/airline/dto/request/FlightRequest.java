package com.airline.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {
    @NotBlank(message = "Flight number is required")
    @Size(max = 10, message = "Flight number must be at most 10 characters")
    private String flightNumber;

    @NotBlank(message = "Departure airport is required")
    private String departureAirport;

    @NotBlank(message = "Arrival airport is required")
    private String arrivalAirport;

    @NotNull(message = "Departure time is required")
    @Future(message = "Departure time must be in the future")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    @Future(message = "Arrival time must be in the future")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Aircraft ID is required")
    private Long aircraftId;

    @NotNull(message = "Base price is required")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private Double basePrice;
}