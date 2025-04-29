package com.ticket.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;
    private String trainNumber;
    private Long passengerId;
    private String seatNumber;
    private String ticketStatus;

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber +
                ", trainNumber='" + trainNumber + '\'' +
                ", passengerId=" + passengerId +
                ", seatNumber='" + seatNumber + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                '}';
    }
// Getters and setters
}