package com.airline.controller;

import com.airline.dto.AircraftDTO;
import com.airline.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircrafts")
@RequiredArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;

    @GetMapping
    public ResponseEntity<List<AircraftDTO>> getAllAircrafts() {
        return ResponseEntity.ok(aircraftService.getAllAircrafts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftDTO> getAircraftById(@PathVariable Long id) {
        return ResponseEntity.ok(aircraftService.getAircraftById(id));
    }

    @PostMapping
    public ResponseEntity<AircraftDTO> createAircraft(@RequestBody AircraftDTO aircraftDTO) {
        return ResponseEntity.ok(aircraftService.createAircraft(aircraftDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AircraftDTO> updateAircraft(
            @PathVariable Long id,
            @RequestBody AircraftDTO aircraftDTO) {
        return ResponseEntity.ok(aircraftService.updateAircraft(id, aircraftDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }
}