/**
 * 
 */
package com.lottery.ticket.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lottery.ticket.application.models.Ticket;

/**
 * @author karthikranjan
 *
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	

	
}
