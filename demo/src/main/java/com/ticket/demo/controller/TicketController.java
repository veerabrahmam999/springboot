package com.ticket.demo.controller;

/*This class is used for accepting http request depend upon the local host address and forward the
 request to the service class*/

import com.ticket.demo.model.Ticket;
import com.ticket.demo.model.TicketRequest;
import com.ticket.demo.service.TicketService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;




/*RestController: Controller+Response Body
This Annotation is used for controlling the request from client and sending the response

 */
@RestController


@RequestMapping("/api")
public class TicketController {
    Logger logger = LoggerFactory.getLogger(TicketController.class);

    //Autowired: for connecting Two classes
    @Autowired
    private TicketService ticketService;


    //PostMapping: to handle incoming HTTP POST requests and stored data in database
    /* IMP:->  postman->RequestBody(Having Data)
    Controller->Service method(generateTicket)->
    in Service Retrive the data from ticket request into Ticket object and send into
    Ticket Repository and then to Database
     */

   @PostMapping("/generate-ticket")
    public Ticket generateTicket(@RequestBody TicketRequest ticketRequest) {

        return ticketService.generateTicket(ticketRequest);
    }

   //Getmapping: to handle incoming HTTP POST requests and retrieve data from database
    //http://localhost:8080/api/fetch-ticket/1

    @GetMapping("/fetch-ticket/{ticketNumber}")
    public Ticket fetchTicketByTicketNumber(@PathVariable Long ticketNumber) {

        logger.info("[fetchTicketByTicketNumber] info message");
        logger.warn("[fetchTicketByTicketNumber] warn message");
        logger.debug("[fetchTicketByTicketNumber] debug message");
        logger.trace("[fetchTicketByTicketNumber] trace message");
        logger.error("[fetchTicketByTicketNumber] error message");
        return ticketService.fetchTicketByTicketNumber(ticketNumber);
    }



    //http://localhost:8080/api/fetch-tickets-by-train/T101
    @GetMapping("/fetch-tickets-by-train/{trainNumber}")
    public List<Ticket> fetchAllTicketsByTrain(@PathVariable String trainNumber) {
        logger.info("fetch-tickets-by-train/{trainNumber} info message");
        logger.warn("fetch-tickets-by-train/{trainNumber} warn message");
        logger.debug("fetch-tickets-by-train/{trainNumber} debug message");
        logger.trace("fetch-tickets-by-train/{trainNumber} trace message");
        logger.error("fetch-tickets-by-train/{trainNumber} error message");
        return ticketService.fetchAllTicketsByTrain(trainNumber);
    }

    //PutMapping: to update the data in database
    //http://localhost:8080/api/cancel-ticket/1
    @PutMapping("/cancel-ticket/{ticketNumber}")
    public boolean cancelTicket(@PathVariable Long ticketNumber) {

        logger.info("cancel-ticket/{ticketNumber} info message");
        logger.warn("cancel-ticket/{ticketNumber} warn message");
        logger.debug("cancel-ticket/{ticketNumber} debug message");
        logger.trace("cancel-ticket/{ticketNumber} trace message");
        logger.error("cancel-ticket/{ticketNumber} error message");

        return ticketService.cancelTicket(ticketNumber);
    }

    //http://localhost:8080/api/cancel-ticket/T102
    @PutMapping("/cancel-tickets-by-train/{trainNumber}")
    public boolean cancelAllTicketsByTrain(@PathVariable String trainNumber) {
        logger.info("cancel-tickets-by-train/{trainNumber} info message");
        logger.warn("cancel-tickets-by-train/{trainNumber} warn message");
        logger.debug("cancel-tickets-by-train/{trainNumber} debug message");
        logger.trace("cancel-tickets-by-train/{trainNumber} trace message");
        logger.error("cancel-tickets-by-train/{trainNumber} error message");
        return ticketService.cancelAllTicketsByTrain(trainNumber);
    }

}
