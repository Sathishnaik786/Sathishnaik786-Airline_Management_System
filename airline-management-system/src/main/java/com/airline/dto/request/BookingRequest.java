package com.airline.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    @NotNull(message = "Flight ID is required")
    private Long flightId;

    @NotNull(message = "Passenger ID is required")
    private Long passengerId;

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private Double totalPrice;
}