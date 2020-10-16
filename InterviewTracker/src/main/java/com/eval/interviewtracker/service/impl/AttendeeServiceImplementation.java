package com.eval.interviewtracker.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.eval.interviewtracker.dto.AttendeeDTO;
import com.eval.interviewtracker.entity.Attendee;
import com.eval.interviewtracker.entity.Interview;
import com.eval.interviewtracker.entity.User;
import com.eval.interviewtracker.exception.RecordNotFoundException;
import com.eval.interviewtracker.repository.IAttendeeRepository;
import com.eval.interviewtracker.repository.IInterviewRepository;
import com.eval.interviewtracker.repository.IUserRepository;
import com.eval.interviewtracker.service.AttendeeService;

@Service
public class AttendeeServiceImplementation implements AttendeeService {

	@Autowired
	IAttendeeRepository attendeeRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IInterviewRepository interviewRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AttendeeDTO> getAllAttendees() {
		List<Attendee> attendeeList = attendeeRepository.findAll();
		return convertToAttendeeDTO(attendeeList);
	}

	@Override
	public List<AttendeeDTO> findAttendeByUserID(Long userId) {
		List<Attendee> attendees = attendeeRepository.findAttendeByUserID(userId);
		if (attendees.size() > 0) {
			return convertToAttendeeDTO(attendees);
		} else {
			throw new RecordNotFoundException("No attendee record present for given User id:",
					String.valueOf(userId));
		}
	}

	@Override
	public List<AttendeeDTO> findAttendeByInterviewID(Long interviewId) {
		List<Attendee> attendees = attendeeRepository.findAttendeByInterviewID(interviewId);
		if (attendees.size() > 0) {
			return convertToAttendeeDTO(attendees);
		} else {
			throw new RecordNotFoundException("No attendee record present for given Interview id:",
					String.valueOf(interviewId));
		}
	}

	@Override
	public AttendeeDTO addAttendee(Long interviewId, Long userId) {
		// TODO Auto-generated method stub

		Optional<User> user = userRepository.findById(userId);
		Optional<Interview> interview = interviewRepository.findById(interviewId);

		if (user.isPresent() && interview.isPresent()) {

			Attendee attendee = new Attendee();
			attendee.setInterview(interview.get());
			attendee.setUser(user.get());

			ExampleMatcher modelMatcher = ExampleMatcher.matching().withIgnorePaths("id")
					.withMatcher("user", match -> match.ignoreCase())
					.withMatcher("interview", match -> match.ignoreCase());
			Example<Attendee> attendeeExample = Example.of(attendee, modelMatcher);

			if (!attendeeRepository.exists(attendeeExample)) {
				Attendee attendeeEntity = attendeeRepository.save(attendee);
				return convertToAttendeeDTO(attendeeEntity);
			} else {
				throw new RecordNotFoundException("record with given data already exists",
						"Inertview ID:" + interviewId + ", User ID:" + userId);
			}

		} else {
			throw new RecordNotFoundException(
					"Interview/User details for given InterviewId/UserId not present. Please enter correct details",
					"Inertview ID:-" + interviewId + ", User ID:-" + userId);
		}

	}

	private AttendeeDTO convertToAttendeeDTO(Attendee attendee) {
		AttendeeDTO attendeeDto = modelMapper.map(attendee, AttendeeDTO.class);
		return attendeeDto;
	}

	private List<AttendeeDTO> convertToAttendeeDTO(List<Attendee> attendee) {
		return attendee.stream().map(x -> convertToAttendeeDTO(x)).collect(Collectors.toList());
	}

}
