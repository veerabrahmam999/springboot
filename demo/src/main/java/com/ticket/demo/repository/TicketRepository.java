package com.ticket.demo.repository;

import com.ticket.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTrainNumber(String trainNumber);


    Optional<Ticket> findByTicketNumber(Long ticketNumber);
}

