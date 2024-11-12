package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Reservation;
import com.example.entities.Seat;
import com.example.repositories.ReservationRepository;
import com.example.repositories.SeatRepository;

@Service
public class CancellationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SeatRepository seatRepository;

//    public Optional<Reservation> cancelReservation(String prn) {
//        Optional<Reservation> reservationOptional = reservationRepository.findByPrn(prn);
//        if (reservationOptional.isPresent()) {
//            reservationRepository.delete(reservationOptional.get());
//            return reservationOptional;
//        }
//        return Optional.empty();
//    }
    public Optional<Reservation> cancelReservation(String prn) {
        Optional<Reservation> reservationOptional = reservationRepository.findByPrn(prn);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            
            // Delete associated seats first
            List<Seat> seats = seatRepository.findByReservationId(reservation.getId());
            for (Seat seat : seats) {
                seatRepository.delete(seat);
            }
            
            // Now delete the reservation
            reservationRepository.delete(reservation);
            return Optional.of(reservation);
        }
        return Optional.empty();
    }
}
