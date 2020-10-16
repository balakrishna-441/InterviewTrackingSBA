package com.eval.interviewtracker.dto;

import com.eval.interviewtracker.entity.Interview;
import com.eval.interviewtracker.entity.User;

import lombok.Data;

@Data
public class AttendeeDTO {
	
	private long id;
	private User user;
	private Interview interview;
}
