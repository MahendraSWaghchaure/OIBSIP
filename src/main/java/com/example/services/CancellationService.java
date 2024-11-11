package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Reservation;
import com.example.repositories.ReservationRepository;

@Service
public class CancellationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Optional<Reservation> cancelReservation(String prn) {
        Optional<Reservation> reservationOptional = reservationRepository.findByPrn(prn);
        if (reservationOptional.isPresent()) {
            reservationRepository.delete(reservationOptional.get());
            return reservationOptional;
        }
        return Optional.empty();
    }
}