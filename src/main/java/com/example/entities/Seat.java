package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String seatNumber;

	@Column(nullable = false)
	private String coach;

	@Column(nullable = false)
	private Boolean isAvailable = true;

	@Column(nullable = false)
	private String seatType; // WINDOW, MIDDLE, AISLE

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "train_id")
	private Train train;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatNumber=" + seatNumber + ", coach=" + coach + ", isAvailable=" + isAvailable
				+ ", seatType=" + seatType + ", train=" + train + ", reservation=" + reservation + "]";
	}

}