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
            seatDetails.append(String.format("Coach: %s, Seat: %s, Type: %s\n", 
                seat.getCoach(), seat.getSeatNumber(), seat.getSeatType()));
        }
        
        String emailText = String.format(
            "Your reservation has been confirmed!\n\n" +
            "PRN: %s\n" +
            "Train: %s\n" +
            "From: %s\n" +
            "To: %s\n" +
            "Date: %s\n" +
            "Number of Tickets: %d\n\n" +
            "Seat Details:\n%s",
            reservation.getPrn(),
            reservation.getTrain().getTrainNumber(),
            reservation.getSource(),
            reservation.getDestination(),
            reservation.getJourneyDate(),
            reservation.getNumTickets(),
            seatDetails.toString()
        );
        
        message.setText(emailText);
        emailSender.send(message);
    }
}



//
//package com.example.services;
//
//import org.springframework.stereotype.Service;
//import com.example.entities.Reservation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//@Service
//public class NotificationService {
//    
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendReservationConfirmation(String email, Reservation reservation) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(email);
//            message.setSubject("Reservation Confirmation - PRN: " + reservation.getPrn());
//            message.setText(buildConfirmationEmailText(reservation));
//            mailSender.send(message);
//        } catch (Exception e) {
//            // Log the error but don't throw it to avoid disrupting the reservation process
//            System.err.println("Failed to send confirmation email: " + e.getMessage());
//        }
//    }
//
//    private String buildConfirmationEmailText(Reservation reservation) {
//        StringBuilder emailText = new StringBuilder();
//        emailText.append("Dear Customer,\n\n");
//        emailText.append("Your reservation has been confirmed.\n\n");
//        emailText.append("Reservation Details:\n");
//        emailText.append("PRN: ").append(reservation.getPrn()).append("\n");
//        emailText.append("Train: ").append(reservation.getTrain().getTrainName())
//                .append(" (").append(reservation.getTrain().getTrainNumber()).append(")\n");
//        emailText.append("From: ").append(reservation.getSource()).append("\n");
//        emailText.append("To: ").append(reservation.getDestination()).append("\n");
//        emailText.append("Journey Date: ").append(reservation.getJourneyDate()).append("\n");
//        emailText.append("Number of Passengers: ").append(reservation.getNumTickets()).append("\n\n");
//        emailText.append("Thank you for choosing our service.\n");
//        return emailText.toString();
//    }
//}