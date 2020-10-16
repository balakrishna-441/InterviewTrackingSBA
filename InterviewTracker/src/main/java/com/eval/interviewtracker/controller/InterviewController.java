package com.eval.interviewtracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.interviewtracker.dto.InterviewDTO;
import com.eval.interviewtracker.service.InterviewService;

@RestController
@RequestMapping("/Interview")
public class InterviewController {

	@Autowired
	InterviewService interviewService;

	@GetMapping("/")
	public ResponseEntity<List<InterviewDTO>> getAllInterview() {
		List<InterviewDTO> list = interviewService.getAllInterviews();
		return new ResponseEntity<List<InterviewDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InterviewDTO> getInterviewById(@PathVariable("id") Long id) {
		InterviewDTO dto = interviewService.getInterviewById(id);
		return new ResponseEntity<InterviewDTO>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/InterviewerName/{name}")
	public ResponseEntity<List<InterviewDTO>> getInterviewByInterviewerName(@PathVariable("name") String name) {
		List<InterviewDTO> dto = interviewService.getInterviewByInterViewerName(name);
		return new ResponseEntity<List<InterviewDTO>>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/InterviewName/{name}")
	public ResponseEntity<List<InterviewDTO>> getInterviewByInterviewName(@PathVariable("name") String name) {
		List<InterviewDTO> dto = interviewService.getInterviewByInterViewName(name);
		return new ResponseEntity<List<InterviewDTO>>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{interviewerName}/{interviewName}")
	public ResponseEntity<List<InterviewDTO>> getInterviewByInterviewName(@PathVariable("interviewerName") String interviewerName,@PathVariable("interviewName") String interviewName) {
		List<InterviewDTO> dto = interviewService.getInterviewByInterViewerandInterviewName(interviewerName, interviewName);
		return new ResponseEntity<List<InterviewDTO>>(dto, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<InterviewDTO> addInterview(@Valid @RequestBody InterviewDTO interview) {
		InterviewDTO dto = interviewService.createInterview(interview);
		return new ResponseEntity<InterviewDTO>(dto, new HttpHeaders(), HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<InterviewDTO> updateInterview(@PathVariable("id") Long id,
			@Valid @RequestBody InterviewDTO interview) {
		InterviewDTO dto = interviewService.updateInterview(id, interview);
		return new ResponseEntity<InterviewDTO>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/UpdateStatus/{id}/{Status}")
	public ResponseEntity<InterviewDTO> updateInterviewStatus(@PathVariable("id") Long id,
			@PathVariable("Status") String status) {
		InterviewDTO dto = interviewService.updateInterviewStatus(id, status);
		return new ResponseEntity<InterviewDTO>(dto, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<InterviewDTO> deleteInterview(@PathVariable("id") Long id) {
		InterviewDTO dto = interviewService.deleteInterview(id);
		return new ResponseEntity<InterviewDTO>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<String> getTotalInterviews() {
		List<InterviewDTO> list = interviewService.getAllInterviews();
		return new ResponseEntity<String>("Total no of interview :- "+list.size(), new HttpHeaders(), HttpStatus.OK);
	}
	
	
}
