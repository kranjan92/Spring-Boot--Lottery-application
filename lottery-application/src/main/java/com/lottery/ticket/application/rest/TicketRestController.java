/**
 * 
 */
package com.lottery.ticket.application.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.lottery.ticket.application.models.Ticket;
import com.lottery.ticket.application.rest.service.TicketService;


/**
 * @author karthikranjan
 *
 */
@RestController
public class TicketRestController {
	
    private static Logger logger = LoggerFactory.getLogger(TicketRestController.class);

    public static final String DEFAULT_LINES = "3";
 
    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     *  Get method to retrieve the specific ticket details
     * @param ticketId
     * @return
     */
    @JsonView(View.Basic.class)
    @RequestMapping(value = "/ticket/{ticketId}", method = RequestMethod.GET)
    public Ticket getTicket(@PathVariable Long ticketId) {
        return ticketService.getTicket(ticketId);
    }

    /**
     * This API uses to ammend or add  the lines to the Lottery ticket and
     * Supports HTTP put method 
     * E.g. Request URI -- http://localhost:8080/ticket/1?lines=5
     * 
     * @param ticketId
     * @param lines
     * @return 
     */
    @JsonView(View.Basic.class)
    @RequestMapping(value = "/ticket/{ticketId}", method = RequestMethod.PUT)
    public Ticket amendTicket(@PathVariable Long ticketId, @RequestParam(value = "lines", defaultValue = DEFAULT_LINES) Optional<Integer> lines) {
        return ticketService.amendTicket(ticketId, lines);
    }

    /**
     * This API is used to mark the status of the ticket and returns the outcomes for each lines.
     * This Supports HTTP put method only
     * E.g. http://localhost:8080/status/1
     * @param ticketId
     * @return
     */
    @RequestMapping(value = "/status/{ticketId}", method = RequestMethod.PUT)
    public Ticket checkTicket(@PathVariable Long ticketId) {
        return ticketService.checkTicket(ticketId);
    }

    /**
     * This API used to create a lottery ticket with n-lines
     * It supports Http POST method
     * E.g. Request: http://localhost:8080/ticket?lines=5
     * @param lines
     * @return
     */
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public ResponseEntity<?> createTicket(@RequestParam(value = "lines", defaultValue = DEFAULT_LINES) Optional<Integer> lines) {
        Ticket ticket = ticketService.createTicket(lines);

        HttpHeaders httpHeaders = new HttpHeaders();

        URI ticketUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(ticket.getId()).toUri();
        httpHeaders.setLocation(ticketUri);
        logger.debug("Creating new ticket with location: {}", ticketUri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * This API used to Retrieve all the Tickets in the Repository.
     * 
     * @return
     */
    @JsonView(View.Basic.class)
    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }



}
