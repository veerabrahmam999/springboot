package com.ticket.demo.model;

//This class is used for retrieving and setting data based on the http request


public class TicketRequest {
    private long ticketNUmber;
    private String trainNumber;
    private Long passengerId;
    private String seatNumber;
    private String ticketStatus;

    public TicketRequest() {
    }

    public TicketRequest(long ticketNUmber, String trainNumber, Long passengerId, String seatNumber, String ticketStatus) {
        this.ticketNUmber = ticketNUmber;
        this.trainNumber = trainNumber;
        this.passengerId = passengerId;
        this.seatNumber = seatNumber;
        this.ticketStatus = ticketStatus;
    }



    //Setters and Getters

    public long getTicketNUmber() {
        return ticketNUmber;
    }

    public void setTicketNUmber(long ticketNUmber) {
        this.ticketNUmber = ticketNUmber;
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







}

