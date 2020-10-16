package com.eval.interviewtracker.exception;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ErrorDetails {
	private Date timestamp;
	private List<String> message;
	private String details;

	public ErrorDetails(Date timestamp, List<String> message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
}