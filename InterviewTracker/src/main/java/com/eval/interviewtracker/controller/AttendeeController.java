package com.eval.interviewtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.interviewtracker.dto.AttendeeDTO;
import com.eval.interviewtracker.service.AttendeeService;

@RestController
@RequestMapping("/attendee")
public class AttendeeController {
	
	@Autowired
	AttendeeService attendenceService;
	
	@GetMapping("/")
	public ResponseEntity<List<AttendeeDTO>> getAllAttendees() {
		List<AttendeeDTO> list = attendenceService.getAllAttendees();
		return new ResponseEntity<List<AttendeeDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/interviewscount")
	public ResponseEntity<String> getTotalInterviews() {
		List<AttendeeDTO> list = attendenceService.getAllAttendees();
		return new ResponseEntity<String>("Total no of interview conducted :- "+list.size(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/add/{interviewId}/{userID}")
	public ResponseEntity<AttendeeDTO> addInterview(@PathVariable("interviewId") Long interviewId,@PathVariable("userID") Long userId) {
		AttendeeDTO dto = attendenceService.addAttendee(interviewId, userId);
		return new ResponseEntity<AttendeeDTO>(dto, new HttpHeaders(), HttpStatus.OK);

	}
	
	@GetMapping("/Interview/{id}")
	public ResponseEntity<List<AttendeeDTO>> getAttendeeByInterviewId(@PathVariable("id") Long id) {
		List<AttendeeDTO> dto = attendenceService.findAttendeByInterviewID(id);
		return new ResponseEntity<List<AttendeeDTO>>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/User/{id}")
	public ResponseEntity<List<AttendeeDTO>> getAttendeeByUserId(@PathVariable("id") Long id) {
		List<AttendeeDTO> dto = attendenceService.findAttendeByUserID(id);
		return new ResponseEntity<List<AttendeeDTO>>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/UserAttendCount/{id}")
	public ResponseEntity<String> getAttendeeCountByUserId(@PathVariable("id") Long id) {
		List<AttendeeDTO> dto = attendenceService.findAttendeByUserID(id);
		return new ResponseEntity<String>("No of interviews attended by user with user id-"+id+" :- "+dto.size(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/InterviewConductedCount/{id}")
	public ResponseEntity<String> getAttendeeCountByInterviewId(@PathVariable("id") Long id) {
		List<AttendeeDTO> dto = attendenceService.findAttendeByInterviewID(id);
		return new ResponseEntity<String>("No of interview conducted for the interview id-"+id+" :- "+dto.size(), new HttpHeaders(), HttpStatus.OK);
	}

}
