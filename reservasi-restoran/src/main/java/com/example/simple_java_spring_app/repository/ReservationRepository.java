package com.example.simple_java_spring_app.repository;

import com.example.simple_java_spring_app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.reservationDate = :reservationDate")
    long countReservationByDate(@Param("reservationDate") LocalDate reservationDate);

    @Query("SELECT r FROM Reservation r WHERE r.reservationDate BETWEEN :startDate AND :endDate")
    List<Reservation> findAllWithinNextWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
