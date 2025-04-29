package com.ticket.demo.repository;

import com.ticket.demo.model.Passenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}

