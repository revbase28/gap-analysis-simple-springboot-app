package com.example.penjualan_tiket_renang.service;

import com.example.penjualan_tiket_renang.entity.Ticket;
import com.example.penjualan_tiket_renang.entity.User;
import com.example.penjualan_tiket_renang.model.AddTicketDto;
import com.example.penjualan_tiket_renang.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket addTicketPurchase(AddTicketDto request, User authenticatedUser){
        Ticket newTicket = new Ticket();
        newTicket.setTicketCode(request.getTicketCode());
        newTicket.setPurchaseDate(new Date());
        newTicket.setPrice(request.getPrice());
        newTicket.setUser(authenticatedUser);

        return ticketRepository.save(newTicket);
    }

    public List<Ticket> getAllPurchasedTicket(){
        return ticketRepository.findAll();
    }
}

