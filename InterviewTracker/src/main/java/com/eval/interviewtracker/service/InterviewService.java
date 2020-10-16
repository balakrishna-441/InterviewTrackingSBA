package com.eval.interviewtracker.service;

import java.util.List;

import com.eval.interviewtracker.dto.InterviewDTO;

public interface InterviewService {
	
	public abstract InterviewDTO createInterview(InterviewDTO User);

	public abstract InterviewDTO updateInterview(Long id,InterviewDTO User);

	public abstract InterviewDTO deleteInterview(Long id);

	public abstract List<InterviewDTO> getAllInterviews();

	public abstract InterviewDTO getInterviewById(Long id);
	
	public abstract List<InterviewDTO> getInterviewByInterViewerName(String name);
	
	public abstract List<InterviewDTO> getInterviewByInterViewerandInterviewName(String interviewerName, String interviewName);
	
	public abstract List<InterviewDTO> getInterviewByInterViewName(String name);
	
	public abstract InterviewDTO updateInterviewStatus(Long id,String status);
}