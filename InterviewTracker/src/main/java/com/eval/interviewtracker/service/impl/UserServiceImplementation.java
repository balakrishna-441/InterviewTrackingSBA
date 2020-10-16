package com.eval.interviewtracker.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.entity.User;
import com.eval.interviewtracker.exception.RecordNotFoundException;
import com.eval.interviewtracker.exception.RecordAlreadyExistsException;
import com.eval.interviewtracker.repository.IUserRepository;
import com.eval.interviewtracker.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	IUserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO user) {
		ExampleMatcher modelMatcher = ExampleMatcher.matching().withIgnorePaths("id")
				.withMatcher("firstName", match -> match.ignoreCase())
				.withMatcher("lastName", match -> match.ignoreCase());
		Example<User> userExample = Example.of(dtoToEntity(user), modelMatcher);
		if (!repository.exists(userExample)) {
			User userEntity = repository.save(dtoToEntity(user));
			return convertToUserDTO(userEntity);
		} else {
			throw new RecordAlreadyExistsException("User details with given data already exists",
					"First Name:" + user.getFirstName() + " & Last Name:" + user.getLastName());
		}
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO user) {
		Optional<User> userExist = repository.findById(id);
		if (userExist.isPresent()) {
			User u = new User();
			u = userExist.get();
			u.setEmail(user.getEmail());
			u.setMobile(user.getMobile());
			u = repository.save(u);
			return convertToUserDTO(u);
		} else {
			ExampleMatcher modelMatcher = ExampleMatcher.matching().withIgnorePaths("id")
					.withMatcher("firstName", match -> match.ignoreCase())
					.withMatcher("lastName", match -> match.ignoreCase());
			Example<User> userExample = Example.of(dtoToEntity(user), modelMatcher);
			if (!repository.exists(userExample)) {
				User userToSave = repository.save(dtoToEntity(user));
				return convertToUserDTO(userToSave);
			} else {
				throw new RecordAlreadyExistsException("User details with given combination already exists- Please enter valid user ID or enter new details:--",
						"First Name:" + user.getFirstName() + " & Last Name:" + user.getLastName());
			}
		}

	}

	@Override
	public UserDTO deleteUser(Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			repository.deleteById(id);
			return convertToUserDTO(user.get());
		} else {
			throw new RecordNotFoundException("No user record exist for given id", String.valueOf(id));
		}
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> UserList = repository.findAll();
		return convertToUserDTO(UserList);
	}

	@Override
	public UserDTO getUserById(Long id) {
		Optional<User> User = repository.findById(id);
		if (User.isPresent()) {
			return convertToUserDTO(User.get());
		} else {
			throw new RecordNotFoundException("No user record exist for given id", String.valueOf(id));
		}
	}

	private UserDTO convertToUserDTO(User user) {
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}

	private List<UserDTO> convertToUserDTO(List<User> user) {
		return user.stream().map(x -> convertToUserDTO(x)).collect(Collectors.toList());
	}

	private User dtoToEntity(UserDTO dto) {
		User userEntity = modelMapper.map(dto, User.class);
		return userEntity;
	}

}
