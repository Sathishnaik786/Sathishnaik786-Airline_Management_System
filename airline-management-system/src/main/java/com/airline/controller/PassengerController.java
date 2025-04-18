package com.airline.controller;

import com.airline.dto.PassengerDTO;
import com.airline.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> getPassengerById(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.getPassengerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDTO> updatePassenger(
            @PathVariable Long id,
            @RequestBody PassengerDTO passengerDTO) {
        return ResponseEntity.ok(passengerService.updatePassenger(id, passengerDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PassengerDTO> getPassengerByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(passengerService.getPassengerByUserId(userId));
    }
}