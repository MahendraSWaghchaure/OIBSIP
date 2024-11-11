package com.example.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {
	private String trainNumber;
	private String source;
	private String destination;
	private Date journeyDate;
	private int numTickets;
	private String email;
	private List<PassengerDto> passengers;
	private SeatDto seatPreference;

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getNumTickets() {
		return numTickets;
	}

	public void setNumTickets(int numTickets) {
		this.numTickets = numTickets;
	}

	public List<PassengerDto> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDto> passengers) {
		this.passengers = passengers;
	}

	public SeatDto getSeatPreference() {
		return seatPreference;
	}

	public void setSeatPreference(SeatDto seatPreference) {
		this.seatPreference = seatPreference;
	}

	@Override
	public String toString() {
		return "ReservationDto [trainNumber=" + trainNumber + ", source=" + source + ", destination=" + destination
				+ ", journeyDate=" + journeyDate + ", numTickets=" + numTickets + ", email=" + email + ", passengers="
				+ passengers + "]";
	}

}