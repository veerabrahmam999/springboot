package com.ticket.demo.service;

/*This class is used for accepting the request from controller class and develop the business logic for the
code and forward to DAO or repository interface*/


import com.ticket.demo.model.Passenger;
import com.ticket.demo.model.Ticket;
import com.ticket.demo.model.TicketRequest;
import com.ticket.demo.repository.PassengerRepository;
import com.ticket.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Service : Service annotation is used for accepting the request from controller class

@Service
public class TicketService {

    //Here Autowired is used for connecting service class with ticketRepository
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;


// This method is used for geenrate ticket based on http request
    public Ticket generateTicket(TicketRequest ticketRequest) {


        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticket.getTicketNumber());
        ticket.setTrainNumber(ticketRequest.getTrainNumber());
        ticket.setPassengerId(ticketRequest.getPassengerId());
        ticket.setSeatNumber(ticketRequest.getSeatNumber());
        ticket.setTicketStatus(ticketRequest.getTicketStatus());
        return ticketRepository.save(ticket);


    }

        //This method is used for fetching ticket number by using http request
    public Ticket fetchTicketByTicketNumber(Long ticketNumber) {
        return ticketRepository.findById(ticketNumber).orElse(null);
    }


    //This method is used for returning List of tickets by using trainNumber
    public List<Ticket> fetchAllTicketsByTrain(String trainNumber) {
        return ticketRepository.findByTrainNumber(trainNumber);
    }



    //This method is used for cancel the ticket by using ticket Number
        public boolean cancelTicket(Long ticketNumber) {
            Optional<Ticket> optionalTicket = ticketRepository.findByTicketNumber(ticketNumber);

            if (optionalTicket.isPresent()) {
                Ticket ticket = optionalTicket.get();
                // Perform any business logic related to cancellation here,
                // such as checking if the cancellation is allowed based on time, status, etc.

                // For a simple cancellation, we can just update the ticket status
                ticket.setTicketStatus("CANCELLED");
                ticketRepository.save(ticket);
                return true; // Cancellation successful
            } else {
                return false; // Ticket not found
            }
        }


        public boolean cancelAllTicketsByTrain(String trainNumber) {
        List<Ticket> tickets = ticketRepository.findByTrainNumber(trainNumber);
        for (Ticket ticket : tickets) {
            ticket.setTicketStatus("CANCELLED");
            ticketRepository.saveAll(tickets);
            return true;
        }


        return false;
    }
    }

