package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ReservationDto;
import com.example.entities.Passenger;
import com.example.entities.Reservation;
import com.example.repositories.PassengerRepository;
import com.example.repositories.ReservationRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public Reservation createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setSource(reservationDto.getSource());
        reservation.setDestination(reservationDto.getDestination());
        reservation.setJourneyDate(reservationDto.getJourneyDate());
        reservation.setNumTickets(reservationDto.getNumTickets());
        
        String prn = UUID.randomUUID().toString(); // Generate PRN
        reservation.setPrn(prn);
        
//        List<Passenger> passengers = reservationDto.getPassengers().stream()
//                .map(passengerDto -> {
//                    Passenger passenger = new Passenger();
//                    passenger.setName(passengerDto.getName());
//                    passenger.setAge(passengerDto.getAge());
//                    passenger.setReservation(reservation);
//                    return passenger;
//                }).toList();
        
        List<Passenger> passengers = reservationDto.getPassengers().stream()
                .map(passengerDto -> {
                    Passenger passenger = new Passenger();
                    passenger.setName(passengerDto.getName());
                    passenger.setAge(passengerDto.getAge());
                    passenger.setReservation(reservation);
                    return passenger;
                })
                .collect(Collectors.toList());

        reservation.setPassengers(passengers);
        return reservationRepository.save(reservation);
    }
    public Reservation getReservation(String prn) {
        return reservationRepository.findByPrn(prn);
    }
}