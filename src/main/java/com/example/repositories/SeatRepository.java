//package com.example.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import com.example.entities.Seat;
//
//import java.awt.print.Pageable;
//import java.util.List;
//
//public interface SeatRepository extends JpaRepository<Seat, Long> {
//    @Query("SELECT s FROM Seat s WHERE s.train.id = :trainId AND s.isAvailable = true ORDER BY s.coach, s.seatNumber")
//    List<Seat> findAvailableSeats(@Param("trainId") Long trainId, @Param("limit") int limit);
//    
//    // Additional useful queries
//    List<Seat> findByTrainIdAndIsAvailable(Long trainId, Boolean isAvailable);
//    
//    @Query("SELECT s FROM Seat s WHERE s.train.id = :trainId AND s.isAvailable = true AND s.seatType = :seatType")
//    List<Seat> findAvailableSeatsByType(@Param("trainId") Long trainId, @Param("seatType") String seatType);
//    
//    @Query("SELECT s FROM Seat s WHERE s.train.id = :trainId AND s.isAvailable = true ORDER BY s.coach, s.seatNumber")
//    List<Seat> findAvailableSeats(@Param("trainId") Long trainId);
//}


package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entities.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.train.id = :trainId AND s.isAvailable = true ORDER BY s.coach, s.seatNumber")
    List<Seat> findAvailableSeats(@Param("trainId") Long trainId);
    
    // Additional useful queries
    List<Seat> findByTrainIdAndIsAvailable(Long trainId, Boolean isAvailable);
    
    // Uncomment this method if you need to filter by seat type
    // @Query("SELECT s FROM Seat s WHERE s.train.id = :trainId AND s.isAvailable = true AND s.seatType = :seatType")
    // List<Seat> findAvailableSeatsByType(@Param("trainId") Long trainId, @Param("seatType") String seatType);
    List<Seat> findByReservationId(Long reservationId);
    
}