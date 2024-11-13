package com.example.entities;

import javax.persistence.*;
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

	@OneToMany(mappedBy = "train")
	private List<Seat> seats;

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
				+ "]";
	}

}
