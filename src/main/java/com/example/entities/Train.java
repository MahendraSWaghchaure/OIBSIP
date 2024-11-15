package com.example.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "trains")
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trainNumber;
	private String trainName;
	private String source;
	private String destination;
	private Integer totalSeats;
	private Double fare;

	@OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
	private List<Seat> seats;
	
	@OneToMany(mappedBy = "train")
    @JsonIgnore  // Prevent recursive serialization
    private List<Reservation> reservations;

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
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

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", trainNumber=" + trainNumber + ", trainName=" + trainName + ", source=" + source
				+ ", destination=" + destination + ", totalSeats=" + totalSeats + ", fare=" + fare + ", seats=" + seats
				+ ", reservations=" + reservations + "]";
	}

	

}
