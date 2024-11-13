package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.PassengerDto;
import com.example.dto.ReservationDto;
import com.example.entities.Passenger;
import com.example.entities.Reservation;
import com.example.entities.Train;
import com.example.exception.ResourceNotFoundException;
import com.example.entities.Seat;
import com.example.repositories.PassengerRepository;
import com.example.repositories.ReservationRepository;
import com.example.repositories.TrainRepository;
import com.example.repositories.SeatRepository;
import com.example.services.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private NotificationService notificationService;

	@Transactional
	public Reservation createReservation(ReservationDto reservationDto) {

		Train train = trainRepository.findByTrainNumber(reservationDto.getTrainNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Train not found"));

		// Fetch all available seats for the train
		List<Seat> allAvailableSeats = seatRepository.findAvailableSeats(train.getId());

		// Limit the list to the desired number of tickets
		if (allAvailableSeats.size() < reservationDto.getNumTickets()) {
			throw new ResourceNotFoundException("Not enough seats available");
		}

		List<Seat> availableSeats = seatRepository.findAvailableSeats(train.getId());
		if (availableSeats.size() < reservationDto.getNumTickets()) {
			throw new ResourceNotFoundException("Not enough seats available");
		}
		availableSeats = availableSeats.subList(0, reservationDto.getNumTickets());

		Reservation reservation = new Reservation();
		reservation.setSource(reservationDto.getSource());
		reservation.setDestination(reservationDto.getDestination());
		reservation.setJourneyDate(reservationDto.getJourneyDate());
		reservation.setNumTickets(reservationDto.getNumTickets());
		reservation.setTrain(train);

		// Save the reservation to make it final
		reservation = reservationRepository.save(reservation);

		// Link each Seat to the Reservation and save
		for (Seat seat : availableSeats) {
			seat.setIsAvailable(false);
			seat.setReservation(reservation);
			seatRepository.save(seat);
		}

		// Create and associate each Passenger with the Reservation using a regular loop
		List<Passenger> passengers = new ArrayList<>();
		for (PassengerDto passengerDto : reservationDto.getPassengers()) {
			Passenger passenger = new Passenger();
			passenger.setName(passengerDto.getName());
			passenger.setAge(passengerDto.getAge());
			passenger.setReservation(reservation);
			passengers.add(passenger);
		}

		// Save all passengers in bulk and link them to the reservation
		passengerRepository.saveAll(passengers);
		reservation.setPassengers(passengers);

		// Send confirmation email with reservation and seat details
		notificationService.sendReservationConfirmation(reservationDto.getEmail(), reservation, availableSeats);

		return reservation;
	}

	public Reservation getReservation(String prn) {
		return reservationRepository.findByPrn(prn)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
	}
}