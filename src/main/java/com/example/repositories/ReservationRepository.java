package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByPrn(String prn);
}
