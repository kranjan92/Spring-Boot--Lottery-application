/**
 * 
 */
package com.lottery.ticket.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author karthikranjan
 *
 */
@ResponseStatus
public class LotteryTicketNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867187303075196536L;


	 public LotteryTicketNotFoundException(Long ticketId) {
	        super("could not find ticket '" + ticketId + "'.");
	    }
	
	}

	
