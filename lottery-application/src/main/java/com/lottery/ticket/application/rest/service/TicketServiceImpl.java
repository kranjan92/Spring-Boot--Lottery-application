/**
 * 
 */
package com.lottery.ticket.application.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lottery.ticket.application.exception.AmendNotAllowedException;
import com.lottery.ticket.application.exception.LotteryTicketNotFoundException;
import com.lottery.ticket.application.exception.TicketPersistException;
import com.lottery.ticket.application.models.Ticket;
import com.lottery.ticket.application.models.TicketModelFactory;
import com.lottery.ticket.application.repository.TicketRepository;



/**
 * @author karthikranjan
 *
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TicketServiceImpl implements TicketService {
	


    private static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;
    private final TicketModelFactory modelFactory;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketModelFactory modelFactory) {
        this.ticketRepository = ticketRepository;
        this.modelFactory = modelFactory;
    }

    @Override
    public Ticket amendTicket(Long ticketId, Optional<Integer> lines) throws AmendNotAllowedException {
        logger.debug("Amending ticket (id: {}) with additional {} lines", ticketId, lines);
        Ticket ticket = validateAndGet(ticketId);
        if (ticket.getStatus() != Ticket.Status.NEW) {
            throw new AmendNotAllowedException(ticketId);
        }
        lines.ifPresent(value ->
                IntStream.range(0, value).forEach(i -> modelFactory.createLine(ticket))
        );
        return saveTicket(ticket);
    }

    @Transactional(readOnly = true)
    @Override
    public Ticket getTicket(Long ticketId) {
        logger.debug("Retrieving ticket with id {}", ticketId);
        return validateAndGet(ticketId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ticket> getTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        logger.debug("Retrieved {} tickets", tickets.size());
        return tickets;
    }

    @Override
    public Ticket checkTicket(Long ticketId) {
        logger.debug("Checking status of ticket with id {}", ticketId);
        Ticket ticket = validateAndGet(ticketId);
        ticket.setStatus(Ticket.Status.CHECKED);
        return saveTicket(ticket);
    }

    @Override
    public Ticket createTicket(Optional<Integer> lines) {
        Ticket ticket = modelFactory.createTicket(lines);
        ticket = saveTicket(ticket);
        logger.debug("Created new ticket with id {}", ticket.getId());
        return ticket;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        Assert.notNull(ticket, "Cannot save ticket: null");
        try {
            ticket = ticketRepository.save(ticket);
        } catch (Exception e) {
            throw new TicketPersistException(e);
        }
        return ticket;
    }

    private Ticket validateAndGet(Long ticketId) {
    	
        Optional<Ticket> optional = this.ticketRepository.findById(ticketId);
        return optional.orElseThrow(() -> new LotteryTicketNotFoundException(ticketId));
    }

    



}
