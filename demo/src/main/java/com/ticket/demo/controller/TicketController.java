package com.ticket.demo.controller;

import com.ticket.demo.model.Ticket;
import com.ticket.demo.model.TicketRequest;
import com.ticket.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

   @PostMapping("/generate-ticket")
    public Ticket generateTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.generateTicket(ticketRequest);
    }

    //http://localhost:8080/api/fetch-ticket/1
    @GetMapping("/fetch-ticket/{ticketNumber}")
    public Ticket fetchTicketByTicketNumber(@PathVariable Long ticketNumber) {
        return ticketService.fetchTicketByTicketNumber(ticketNumber);
    }



    //http://localhost:8080/api/fetch-tickets-by-train/T101
    @GetMapping("/fetch-tickets-by-train/{trainNumber}")
    public List<Ticket> fetchAllTicketsByTrain(@PathVariable String trainNumber) {
        return ticketService.fetchAllTicketsByTrain(trainNumber);
    }

    //http://localhost:8080/api/cancel-ticket/1
    @PutMapping("/cancel-ticket/{ticketNumber}")
    public void cancelTicket(@PathVariable Long ticketNumber) {
        ticketService.cancelTicket(ticketNumber);
    }

    //http://localhost:8080/api/cancel-ticket/T102
    @PutMapping("/cancel-tickets-by-train/{trainNumber}")
    public void cancelAllTicketsByTrain(@PathVariable String trainNumber) {
        ticketService.cancelAllTicketsByTrain(trainNumber);
    }
}
