package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.entities.Reservation;
import com.example.entities.Seat;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendReservationConfirmation(String email, Reservation reservation, List<Seat> seats) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Reservation Confirmation - PRN: " + reservation.getPrn());

		StringBuilder seatDetails = new StringBuilder();
		for (Seat seat : seats) {
			seatDetails.append(String.format("Coach: %s, Seat: %s, Type: %s\n", seat.getCoach(), seat.getSeatNumber(),
					seat.getSeatType()));
		}

		String emailText = String.format(
				"Your reservation has been confirmed!\n\n" + "PRN: %s\n" + "Train: %s\n" + "From: %s\n" + "To: %s\n"
						+ "Date: %s\n" + "Number of Tickets: %d\n\n" + "Seat Details:\n%s",
				reservation.getPrn(), reservation.getTrain().getTrainNumber(), reservation.getSource(),
				reservation.getDestination(), reservation.getJourneyDate(), reservation.getNumTickets(),
				seatDetails.toString());

		message.setText(emailText);
		emailSender.send(message);
	}
}
