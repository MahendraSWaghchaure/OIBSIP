package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Reservation;
import com.example.repositories.ReservationRepository;

@Service
public class CancellationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation cancelReservation(String prn) {
        Reservation reservation = reservationRepository.findByPrn(prn);
        if (reservation != null) {
            reservationRepository.delete(reservation);
        }
        return reservation;
    }
}