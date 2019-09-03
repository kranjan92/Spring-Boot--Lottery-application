/**
 * 
 */
package com.lottery.ticket.application.exception;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author karthikranjan
 *
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	/**
	 * Default message for all other exceptions
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<Object> TicketNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Ticket Not Found",
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	/**
	 * Exception is thrown by the service layer when trying to retrieve ticket which doesn't exist
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(LotteryTicketNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(LotteryTicketNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	/**
	 * Validation exception ( invalid parameter in the request )
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
	
	/**
	 * Exception is thrown when trying to add the lines into ticket when ticket is "checked"
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AmendNotAllowedException.class)
	public final ResponseEntity<Object> amendNotAllowedExceptionExceptionHandler(AmendNotAllowedException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	/**
	 * Exception Thrown when trying to access the repository in an invalid way or implementation error
	 * while accesing the repos.
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public final ResponseEntity<Object> invalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Exception is thrown when trying to save the null id ticket in the repos.
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(TicketPersistException.class)
	public final ResponseEntity<Object> illegalArgumentExceptionHandler(TicketPersistException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
