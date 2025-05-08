package com.ticket.demo.model;
import jakarta.persistence.*;


/*Entity: Marks a Java class as a JPA entity, indicating that it represents a table in the database. */
@Entity

/* Table: Specifies the name of the database table to which this entity is mapped.
If you don't specify it, the table name will default to the class name.
 */
@Table(name = "tickets")
public class Ticket {
    /* ID: Designates the primary key field of the entity.*/
    @Id
    //Generated Value: For auto incrementing the primary key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;
    private String trainNumber;
    private Long passengerId;
    private String seatNumber;
    private String ticketStatus;

    public Ticket(Long ticketNumber, String trainNumber, Long passengerId, String seatNumber, String ticketStatus) {
        this.ticketNumber = ticketNumber;
        this.trainNumber = trainNumber;
        this.passengerId = passengerId;
        this.seatNumber = seatNumber;
        this.ticketStatus = ticketStatus;
    }

    public Ticket() {

    }

    public Ticket(long l, String alice, String t201) {
    }

    //Getters and Setters
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

}