package com.eval.interviewtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordAlreadyExistsException extends RuntimeException {

	public RecordAlreadyExistsException(String exceptionDetail, String value) {
		super(exceptionDetail + " - " + value);
	}

}