package com.eval.interviewtracker.service;

import java.util.List;

import com.eval.interviewtracker.dto.UserDTO;

public interface UserService {
	
	public abstract UserDTO createUser(UserDTO User);

	public abstract UserDTO updateUser(Long id,UserDTO User);

	public abstract UserDTO deleteUser(Long id);

	public abstract List<UserDTO> getAllUser();

	public abstract UserDTO getUserById(Long id);
}