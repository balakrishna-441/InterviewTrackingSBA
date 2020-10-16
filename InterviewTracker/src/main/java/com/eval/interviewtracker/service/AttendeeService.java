package com.eval.interviewtracker.service;

import java.util.List;

import com.eval.interviewtracker.dto.AttendeeDTO;

public interface AttendeeService {

	public abstract AttendeeDTO addAttendee(Long interviewId,Long userId);
	
	public abstract List<AttendeeDTO> getAllAttendees();
	
	public abstract List<AttendeeDTO> findAttendeByUserID(Long userId);
	
	public abstract List<AttendeeDTO> findAttendeByInterviewID(Long interviewId);
	
	
}
