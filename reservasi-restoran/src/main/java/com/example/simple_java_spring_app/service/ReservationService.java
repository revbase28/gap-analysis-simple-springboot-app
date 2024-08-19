package com.example.simple_java_spring_app.service;

import com.example.simple_java_spring_app.entity.Reservation;
import com.example.simple_java_spring_app.model.AddReservationDTO;
import com.example.simple_java_spring_app.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation addNewReservation(AddReservationDTO addReservationDTO) throws Exception {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, addReservationDTO.getReservationDate());
        if(daysBetween > 7){
            throw new Exception("Reservation can only be made up to 7 days in advance");
        }

        DayOfWeek dayOfWeek = addReservationDTO.getReservationDate().getDayOfWeek();

        if(dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.WEDNESDAY){
            throw new Exception("Reservation cannot be made in Wednesday of Friday");
        }

        long reservationCount = reservationRepository.countReservationByDate(addReservationDTO.getReservationDate());

        if(reservationCount >= 2){
            throw new Exception("Reservation limit reached for the selected date");
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(addReservationDTO.getReservationDate());
        reservation.setName(addReservationDTO.getName());
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findReservationWithinOneWeek(){
        LocalDate today = LocalDate.now();
        return reservationRepository.findAllWithinNextWeek(today, today.plusDays(7));
    }
}
