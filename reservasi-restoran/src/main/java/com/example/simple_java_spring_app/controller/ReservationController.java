package com.example.simple_java_spring_app.controller;

import com.example.simple_java_spring_app.entity.Reservation;
import com.example.simple_java_spring_app.model.AddReservationDTO;
import com.example.simple_java_spring_app.model.WebResponse;
import com.example.simple_java_spring_app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<WebResponse<Reservation>> addNewReservation(@RequestBody AddReservationDTO request){
        try{
            Reservation reservation = reservationService.addNewReservation(request);
            WebResponse<Reservation> response = WebResponse.<Reservation>builder()
                    .data(reservation)
                    .message("Reservation Success").build();
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            WebResponse<Reservation> response = WebResponse.<Reservation>builder()
                    .message(exception.getMessage())
                    .data(null).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<WebResponse<List<Reservation>>> getAllReservationWithinOneWeek(){
        WebResponse<List<Reservation>> response = WebResponse.<List<Reservation>>builder()
                .message("Fetching Successfull")
                .data(reservationService.findReservationWithinOneWeek()).build();

        return ResponseEntity.ok(response);
    }
}
