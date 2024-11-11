package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
   Optional< Reservation> findByPrn(String prn);
}
