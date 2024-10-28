package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.ReservationDto;
import com.example.entities.Reservation;
import com.example.services.ReservationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        try {
            Reservation reservation = reservationService.createReservation(reservationDto);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{prn}")
    public ResponseEntity<?> getReservation(@PathVariable String prn) {
        try {
            Reservation reservation = reservationService.getReservation(prn);
            if (reservation != null) {
                return new ResponseEntity<>(reservation, HttpStatus.OK);
            }
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}