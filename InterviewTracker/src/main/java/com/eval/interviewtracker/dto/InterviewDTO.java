package com.eval.interviewtracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InterviewDTO {
	
	private long id;

	@NotBlank(message = "{interview.interviewername}")
	@Size(min = 5, max = 30, message = "{interview.interviewername.invalid}")
	private String interviewerName;

	@NotBlank(message = "{interview.interviewname}")
	@Size(min = 3, max = 30, message = "{interview.interviewname.invalid}")
	private String interviewName;

	@NotBlank(message = "{interview.userskills}")
	@Size(min = 5, max = 30, message = "{interview.userskills.invalid}")
	private String userSkills;

	private LocalTime time;

	private LocalDate date;

	@NotBlank(message = "{interview.status}")
	@Size(min = 5, max = 100, message = "{interview.status.invalid}")
	private String interviewStatus;

	@NotBlank(message = "{interview.remarks}")
	@Size(min = 5, max = 100, message = "{interview.remarks.invalid}")
	private String remarks;
	
	
}
