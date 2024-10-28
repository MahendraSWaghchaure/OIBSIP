package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
