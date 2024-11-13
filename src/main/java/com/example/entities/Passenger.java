package com.example.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "passengers")
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", age=" + age + ", reservation=" + reservation + "]";
	}

}