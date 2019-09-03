/**
 * 
 */
package com.lottery.ticket.application.rest.service;

import java.util.List;
import java.util.Optional;

import com.lottery.ticket.application.exception.AmendNotAllowedException;
import com.lottery.ticket.application.models.Ticket;


/**
 * @author karthikranjan
 *
 */
public interface TicketService {
	


    /**
     * Add the additional lines to the ticket. If the ticket's status is {@link Ticket.Status = Checked} 
     * Otherwise throw {@link AmendNotAllowedException}
     *
     * @param ticketId the ticket id
     * @param lines    the optional number of lines to add
     * @return the updated ticket
     * @throws AmendNotAllowedException if the ticket is already checked
     */
    Ticket amendTicket(Long ticketId, Optional<Integer> lines) throws AmendNotAllowedException;

    /**
     * Retrieve a single ticket
     *
     * @param ticketId the ticket id
     * @return the ticket
     */
    Ticket getTicket(Long ticketId);

    /**
     * Retrieve a list of all the tickets
     *
     * @return the list of all tickets
     */
    List<Ticket> getTickets();

    /**
     * Check the ticket, effectively setting its status to {@link Ticket.Status = CHECKED}
     *
     * @param ticketId the ticket id
     * @return the checked ticket
     */
    Ticket checkTicket(Long ticketId);

    /**
     * Create a ticket with a (optional) number of lines
     *
     * @param lines an optional number of lines to initialize the ticket
     * @return the new ticket
     */
    Ticket createTicket(Optional<Integer> lines);

    /**
     * Persist the passed {@link Ticket}
     * @param ticket the ticket to be saved
     * @return the saved ticket
     */
    Ticket saveTicket(Ticket ticket);


}
