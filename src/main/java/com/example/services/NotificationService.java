//package com.example.services;
//
//import com.example.entities.Passenger;
//import com.example.entities.Reservation;
//import com.example.entities.Seat;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//@Service
//public class NotificationService {
//
//    @Autowired
//    private JavaMailSender emailSender;
//
//    public void sendReservationConfirmation(String email, Reservation reservation, List<Seat> seats) {
//        try {
//            // Generate the PDF
//            String pdfPath = generateReservationPDF(reservation, seats);
//
//            // Create a MimeMessage
//            MimeMessage message = emailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true); // 'true' indicates multipart message
//
//            helper.setTo(email);
//            helper.setSubject("🎉 Reservation Confirmation - PRN: " + reservation.getPrn());
//
//            // Build the email text
//            StringBuilder seatDetails = new StringBuilder();
//            for (Seat seat : seats) {
//                seatDetails.append(String.format("• Coach: %s | Seat: %s | Type: %s\n",
//                        seat.getCoach(), seat.getSeatNumber(), seat.getSeatType()));
//            }
//
//            StringBuilder passengerNames = new StringBuilder();
//            for (Passenger passenger : reservation.getPassengers()) {
//                passengerNames.append("• ").append(passenger.getName()).append("\n");
//            }
//
//            String emailText = String.format(
//                    "Hello %s,\n\n" +
//                            "✨ Thank you for choosing our service! Your reservation has been successfully confirmed.\n\n" +
//                            "📅 Reservation Details:\n" +
//                            "• PRN: %s\n" +
//                            "• Train: %s (%s)\n" +
//                            "• From: %s\n" +
//                            "• To: %s\n" +
//                            "• Date: %s\n" +
//                            "• Number of Tickets: %d\n\n" +
//                            "👥 Passengers:\n%s\n" +
//                            "🪑 Seat Details:\n%s\n\n" +
//                            "Safe travels, and enjoy your journey!\n\n" +
//                            "Best regards,\nYour Reservation Team",
//                    reservation.getPassengers().get(0).getName(), // Assuming the first passenger name as primary
//                    reservation.getPrn(),
//                    reservation.getTrain().getTrainName(),
//                    reservation.getTrain().getTrainNumber(),
//                    reservation.getSource(),
//                    reservation.getDestination(),
//                    reservation.getJourneyDate().toString(),
//                    reservation.getNumTickets(),
//                    passengerNames.toString(),
//                    seatDetails.toString()
//            );
//
//            helper.setText(emailText);
//
//            // Attach the PDF
//            File pdfFile = new File(pdfPath);
//            helper.addAttachment("Reservation_" + reservation.getPrn() + ".pdf", pdfFile);
//
//            // Send the email
//            emailSender.send(message);
//
//            System.out.println("Email sent successfully with PDF attachment.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String generateReservationPDF(Reservation reservation, List<Seat> seats) throws Exception {
//        String fileName = "Reservation_" + reservation.getPrn() + ".pdf";
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(fileName));
//        document.open();
//
//        // Title and reservation details
//        document.add(new Paragraph("Reservation Confirmation", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
//        document.add(new Paragraph("PRN: " + reservation.getPrn()));
//        document.add(new Paragraph("Train: " + reservation.getTrain().getTrainName() + " (" + reservation.getTrain().getTrainNumber() + ")"));
//        document.add(new Paragraph("From: " + reservation.getSource() + " To: " + reservation.getDestination()));
//        document.add(new Paragraph("Date: " + reservation.getJourneyDate()));
//        document.add(new Paragraph("Number of Tickets: " + reservation.getNumTickets()));
//        document.add(Chunk.NEWLINE);
//
//        // Passenger details
//        document.add(new Paragraph("Passengers:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
//        for (Passenger passenger : reservation.getPassengers()) {
//            document.add(new Paragraph("• " + passenger.getName() + " (Age: " + passenger.getAge() + ")"));
//        }
//        document.add(Chunk.NEWLINE);
//
//        // Seat details
//        document.add(new Paragraph("Seat Details:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
//        for (Seat seat : seats) {
//            document.add(new Paragraph(String.format("• Coach: %s | Seat: %s | Type: %s", seat.getCoach(), seat.getSeatNumber(), seat.getSeatType())));
//        }
//
//        document.close();
//
//        System.out.println("PDF generated successfully: " + fileName);
//        return fileName; // Return the path of the generated PDF
//    }
//}



package com.example.services;

import com.example.entities.Passenger;
import com.example.entities.Reservation;
import com.example.entities.Seat;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendReservationConfirmation(String email, Reservation reservation, List<Seat> seats) {
        try {
            // Generate the PDF
            String pdfPath = generateReservationPDF(reservation, seats);

            // Create a Message
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // 'true' indicates multipart message

            helper.setTo(email);
            helper.setSubject("🎉 Reservation Confirmation - PRN: " + reservation.getPrn());

            // Build the email text (HTML-based)
            StringBuilder passengerNames = new StringBuilder();
            for (Passenger passenger : reservation.getPassengers()) {
                passengerNames.append("<li>").append(passenger.getName()).append("</li>");
            }

            StringBuilder seatDetails = new StringBuilder();
            for (Seat seat : seats) {
                seatDetails.append("<li>Coach: ").append(seat.getCoach())
                           .append(" | Seat: ").append(seat.getSeatNumber())
                           .append(" | Type: ").append(seat.getSeatType())
                           .append("</li>");
            }

            String emailText = String.format(
                "<html>" +
                    "<body>" +
                        "<h2>Hello %s,</h2>" +
                        "<p>✨ Thank you for choosing our service! Your reservation has been successfully confirmed.</p>" +
                        "<h3>📅 Reservation Details:</h3>" +
                        "<ul>" +
                            "<li><b>PRN:</b> %s</li>" +
                            "<li><b>Train:</b> %s (%s)</li>" +
                            "<li><b>From:</b> %s</li>" +
                            "<li><b>To:</b> %s</li>" +
                            "<li><b>Date:</b> %s</li>" +
                            "<li><b>Number of Tickets:</b> %d</li>" +
                        "</ul>" +
                        "<h3>👥 Passengers:</h3>" +
                        "<ul>%s</ul>" +
                        "<h3>🪑 Seat Details:</h3>" +
                        "<ul>%s</ul>" +
                        "<p>Safe travels, and enjoy your journey!</p>" +
                        "<p><b>Best regards,</b><br>Your Reservation Team</p>" +
                    "</body>" +
                "</html>",
                reservation.getPassengers().get(0).getName(), // Assuming the first passenger name as primary
                reservation.getPrn(),
                reservation.getTrain().getTrainName(),
                reservation.getTrain().getTrainNumber(),
                reservation.getSource(),
                reservation.getDestination(),
                new SimpleDateFormat("dd-MM-yyyy").format(reservation.getJourneyDate()),
                reservation.getNumTickets(),
                passengerNames.toString(),
                seatDetails.toString()
            );

            helper.setText(emailText, true); // Enable HTML content

            // Attach the PDF
            File pdfFile = new File(pdfPath);
            helper.addAttachment("Reservation_" + reservation.getPrn() + ".pdf", pdfFile);

            // Send the email
            emailSender.send(message);

            System.out.println("Email sent successfully with PDF attachment.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateReservationPDF(Reservation reservation, List<Seat> seats) throws Exception {
        String fileName = "Reservation_" + reservation.getPrn() + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        // Add a header
        Paragraph header = new Paragraph("Train Reservation Ticket", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLUE));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(Chunk.NEWLINE);

        // Add reservation details in a table
        PdfPTable detailsTable = new PdfPTable(2);
        detailsTable.setWidthPercentage(100);
        detailsTable.setSpacingBefore(10f);

        addTableRow(detailsTable, "PRN:", reservation.getPrn());
        addTableRow(detailsTable, "Train:", reservation.getTrain().getTrainName() + " (" + reservation.getTrain().getTrainNumber() + ")");
        addTableRow(detailsTable, "From:", reservation.getSource());
        addTableRow(detailsTable, "To:", reservation.getDestination());
        addTableRow(detailsTable, "Date:", new SimpleDateFormat("dd-MM-yyyy").format(reservation.getJourneyDate()));
        addTableRow(detailsTable, "Number of Tickets:", String.valueOf(reservation.getNumTickets()));
        document.add(detailsTable);

        // Add passenger details
        document.add(new Paragraph("Passengers", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        PdfPTable passengersTable = new PdfPTable(2);
        passengersTable.setWidthPercentage(100);
        passengersTable.setSpacingBefore(10f);

        addTableHeader(passengersTable, "Name", "Age");
        for (Passenger passenger : reservation.getPassengers()) {
            addTableRow(passengersTable, passenger.getName(), String.valueOf(passenger.getAge()));
        }
        document.add(passengersTable);

        // Add seat details
        document.add(new Paragraph("Seat Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        PdfPTable seatsTable = new PdfPTable(3);
        seatsTable.setWidthPercentage(100);
        seatsTable.setSpacingBefore(10f);

        addTableHeader(seatsTable, "Coach", "Seat Number", "Type");
        for (Seat seat : seats) {
            addTableRow(seatsTable, seat.getCoach(), seat.getSeatNumber(), seat.getSeatType());
        }
        document.add(seatsTable);

        // Add a footer
        Paragraph footer = new Paragraph("Thank you for choosing us. Have a safe journey!", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.GRAY));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(20f);
        document.add(footer);

        document.close();
        return fileName;
    }

    private void addTableRow(PdfPTable table, String key, String value) {
        table.addCell(new PdfPCell(new Phrase(key, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
        table.addCell(new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.HELVETICA, 12))));
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
            headerCell.setBackgroundColor(BaseColor.DARK_GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
        }
    }
    private void addTableRow(PdfPTable table, String column1, String column2, String column3) {
        table.addCell(new PdfPCell(new Phrase(column1, FontFactory.getFont(FontFactory.HELVETICA, 12))));
        table.addCell(new PdfPCell(new Phrase(column2, FontFactory.getFont(FontFactory.HELVETICA, 12))));
        table.addCell(new PdfPCell(new Phrase(column3, FontFactory.getFont(FontFactory.HELVETICA, 12))));
    }
}
