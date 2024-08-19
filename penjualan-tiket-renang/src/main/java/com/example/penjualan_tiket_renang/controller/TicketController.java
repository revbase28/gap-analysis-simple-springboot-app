package com.example.penjualan_tiket_renang.controller;

import com.example.penjualan_tiket_renang.entity.Ticket;
import com.example.penjualan_tiket_renang.entity.User;
import com.example.penjualan_tiket_renang.model.AddTicketDto;
import com.example.penjualan_tiket_renang.model.RegisterUserDto;
import com.example.penjualan_tiket_renang.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> addNewTicket(@RequestBody AddTicketDto addTicketDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Ticket ticket = ticketService.addTicketPurchase(addTicketDto, currentUser);

        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTicket(){
        return ResponseEntity.ok(ticketService.getAllPurchasedTicket());
    }
}
