package com.eval.interviewtracker.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(RecordNotFoundException ex,
			WebRequest request) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RecordAlreadyExistsException.class)
	public final ResponseEntity<ErrorDetails> handleUserExistsException(RecordAlreadyExistsException ex,
			WebRequest request) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> message = new ArrayList<String>();
		message.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       
        List<String> message = new ArrayList<String>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {   
        	message.add(error.getDefaultMessage());           
        }
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       
        List<String> message = new ArrayList<String>();
        message.add(ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }
}