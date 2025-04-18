package com.airline.dto.response;

import com.airline.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private String bookingReference;
    private Long flightId;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private Long passengerId;
    private String passengerName;
    private String seatNumber;
    private LocalDateTime bookingDate;
    private BookingStatus bookingStatus;
    private Double totalPrice;
}