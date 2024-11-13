package com.example.dto;

public class SeatDto {
	private String preferredCoach;
	private String preferredSeatType;

	// Getters and Setters
	public String getPreferredCoach() {
		return preferredCoach;
	}

	public void setPreferredCoach(String preferredCoach) {
		this.preferredCoach = preferredCoach;
	}

	public String getPreferredSeatType() {
		return preferredSeatType;
	}

	public void setPreferredSeatType(String preferredSeatType) {
		this.preferredSeatType = preferredSeatType;
	}
}