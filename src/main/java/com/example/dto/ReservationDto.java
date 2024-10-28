package com.example.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {
    private String source;
    private String destination;
    private Date journeyDate;
    private int numTickets;
    private List<PassengerDto> passengers;
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
	@Override
	public String toString() {
		return "ReservationDto [source=" + source + ", destination=" + destination + ", journeyDate=" + journeyDate
				+ ", numTickets=" + numTickets + ", passengers=" + passengers + "]";
	}
    
    
}