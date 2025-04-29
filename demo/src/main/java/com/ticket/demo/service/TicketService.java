package com.ticket.demo.service;

import com.ticket.demo.model.Passenger;
import com.ticket.demo.model.Ticket;
import com.ticket.demo.model.TicketRequest;
import com.ticket.demo.repository.PassengerRepository;
import com.ticket.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;



    public Ticket generateTicket(TicketRequest ticketRequest) {

        Passenger passenger = new Passenger();

        passenger.setName(ticketRequest.getPassengerName());
        passenger.setAge(ticketRequest.getPassengerAge());
        passenger.setGender(ticketRequest.getPassengerGender());
        passenger = passengerRepository.save(passenger);

        Ticket ticket = new Ticket();
        ticket.setTrainNumber(ticketRequest.getTrainNumber());
        ticket.setPassengerId(passenger.getPassengerId());
        ticket.setSeatNumber(ticketRequest.getSeatNumber());
        return ticketRepository.save(ticket);
    }

    public Ticket fetchTicketByTicketNumber(Long ticketNumber) {
        return ticketRepository.findById(ticketNumber).orElse(null);
    }

    public List<Ticket> fetchAllTicketsByTrain(String trainNumber) {
        return ticketRepository.findByTrainNumber(trainNumber);
    }

    public void cancelTicket(Long ticketNumber) {
        Ticket ticket = ticketRepository.findById(ticketNumber).orElse(null);
        if (ticket != null) {
            ticket.setTicketStatus("CANCELLED");
            ticketRepository.save(ticket);
        }
    }

    public void cancelAllTicketsByTrain(String trainNumber) {
        List<Ticket> tickets = ticketRepository.findByTrainNumber(trainNumber);
        for (Ticket ticket : tickets) {
            ticket.setTicketStatus("CANCELLED");
        }
        ticketRepository.saveAll(tickets);
    }
}
