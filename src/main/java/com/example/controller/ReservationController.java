package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.services.ReservationService;
import com.example.dto.ReservationDto;
import com.example.entities.Reservation;

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
			return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{prn}")
	public ResponseEntity<?> getReservation(@PathVariable String prn) {
		try {
			Reservation reservation = reservationService.getReservation(prn);
			return ResponseEntity.ok(reservation);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}