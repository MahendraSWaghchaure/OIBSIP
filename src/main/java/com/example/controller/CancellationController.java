package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entities.Reservation;
import com.example.services.CancellationService;

@RestController
@RequestMapping("/api/cancellations")
@CrossOrigin(origins = "*")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;

    @PostMapping("/{prn}")
    public ResponseEntity<?> cancelReservation(@PathVariable String prn) {
        try {
            Reservation cancelledReservation = cancellationService.cancelReservation(prn);
            if (cancelledReservation != null) {
                return new ResponseEntity<>("Reservation cancelled successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}