package com.eval.interviewtracker.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.interviewtracker.dto.InterviewDTO;
import com.eval.interviewtracker.entity.Interview;
import com.eval.interviewtracker.exception.RecordNotFoundException;
import com.eval.interviewtracker.repository.IInterviewRepository;
import com.eval.interviewtracker.service.InterviewService;

@Service
public class InterviewServiceImplementation implements InterviewService {

	@Autowired
	IInterviewRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public InterviewDTO createInterview(InterviewDTO interview) {
		Interview InterviewEntity = repository.save(dtoToEntity(interview));
		return convertToInterviewDTO(InterviewEntity);
	}

	@Override
	public InterviewDTO updateInterview(Long id, InterviewDTO interview) {
		
		Optional<Interview> interviewExist = repository.findById(id);
		if (interviewExist.isPresent()) {
			Interview interviewEntity = new Interview();
			interviewEntity = interviewExist.get();
			interviewEntity.setInterviewerName(interview.getInterviewerName());
			interviewEntity.setInterviewName(interview.getInterviewName());
			interviewEntity.setInterviewStatus(interview.getInterviewStatus());
			interviewEntity.setRemarks(interview.getRemarks());
			interviewEntity.setUserSkills(interview.getUserSkills());
			interviewEntity = repository.save(interviewEntity);
			return convertToInterviewDTO(interviewEntity);
		} else {
			Interview interviewEntity = repository.save(dtoToEntity(interview));
			return convertToInterviewDTO(interviewEntity);
		}
	}

	@Override
	public InterviewDTO deleteInterview(Long id) {
		Optional<Interview> User = repository.findById(id);

		if (User.isPresent()) {
			repository.deleteById(id);
			return convertToInterviewDTO(User.get());
		} else {
			throw new RecordNotFoundException("No interview record exist for given id", String.valueOf(id));
		}
	}

	@Override
	public List<InterviewDTO> getAllInterviews() {
		List<Interview> interviewList = repository.findAll();
		return convertToInterviewDTO(interviewList);
	}

	@Override
	public InterviewDTO getInterviewById(Long id) {
		Optional<Interview> interview = repository.findById(id);
		if (interview.isPresent()) {
			return convertToInterviewDTO(interview.get());
		} else {
			throw new RecordNotFoundException("No interview record exist for given id", String.valueOf(id));
		}
	}

	@Override
	public List<InterviewDTO> getInterviewByInterViewerName(String interviewerName) {
		List<Interview> interview = repository.getInterviewByInterviewerName(interviewerName);
		if (interview.size()>0) {
			return convertToInterviewDTO(interview);
		} else {
			throw new RecordNotFoundException("No interview record exist for given interviewer name", interviewerName);
		}
	}

	@Override
	public List<InterviewDTO> getInterviewByInterViewName(String interviewName) {
		List<Interview> interview = repository.getInterviewByInterviewName(interviewName);
		if (interview.size()>0) {
			return convertToInterviewDTO(interview);
		} else {
			throw new RecordNotFoundException("No interview record exist for given interview name", interviewName);
		}
	}
	
	@Override
	public List<InterviewDTO> getInterviewByInterViewerandInterviewName(String interviewerName, String interviewName) {
		List<Interview> interview = repository.getInterviewByInterviewerandInterviewName(interviewerName, interviewName);
		if (interview.size()>0) {
			return convertToInterviewDTO(interview);
		} else {
			throw new RecordNotFoundException(
					"Interview details for given Interviewer/Interview Name not present. Please enter correct details",
					"Interviewer Name:-" + interviewerName + ", Interview Name:-" + interviewName);
		}
	}

	@Override
	public InterviewDTO updateInterviewStatus(Long id, String status) {
		Optional<Interview> interview = repository.findById(id);
		if (interview.isPresent()) {
			Interview interviewUpdate = interview.get();
			interviewUpdate.setInterviewStatus(status);
			interviewUpdate = repository.save(interviewUpdate);
			return convertToInterviewDTO(interviewUpdate);
		} else {
			throw new RecordNotFoundException("No interview record exist for given id", String.valueOf(id));
		}
	}

	private InterviewDTO convertToInterviewDTO(Interview interview) {
		InterviewDTO InterviewDTO = modelMapper.map(interview, InterviewDTO.class);
		return InterviewDTO;
	}

	private List<InterviewDTO> convertToInterviewDTO(List<Interview> user) {
		return user.stream().map(x -> convertToInterviewDTO(x)).collect(Collectors.toList());
	}

	private Interview dtoToEntity(InterviewDTO dto) {
		Interview InterviewEntity = modelMapper.map(dto, Interview.class);
		return InterviewEntity;
	}

	

}
