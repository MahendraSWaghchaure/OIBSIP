package com.example.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String source;

    @NotNull
    private String destination;

    @NotNull
    private Date journeyDate;

    @NotNull
    private int numTickets;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    private String prn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", source=" + source + ", destination=" + destination + ", journeyDate="
				+ journeyDate + ", numTickets=" + numTickets + ", passengers=" + passengers + ", prn=" + prn + "]";
	}

   
    
}
