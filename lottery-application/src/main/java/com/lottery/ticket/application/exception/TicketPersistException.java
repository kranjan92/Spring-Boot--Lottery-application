/**
 * 
 */
package com.lottery.ticket.application.exception;

/**
 * @author karthikranjan
 *
 */
public class TicketPersistException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3742441397855408358L;

	public TicketPersistException() {
		
	}
	
	 public TicketPersistException(Exception e) {
	        super("could not save ticket: " + e.getMessage(), e);
	    }



}
