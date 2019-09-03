package com.lottery.ticket.application.exception;

public class AmendNotAllowedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3806919524467314524L;

	public AmendNotAllowedException(Long ticketId) {
        super("cannot amend ticket '" + ticketId + "'.");
    }

}
