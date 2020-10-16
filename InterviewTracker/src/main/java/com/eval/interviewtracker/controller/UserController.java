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

import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		List<UserDTO> list = userService.getAllUser();
		return new ResponseEntity<List<UserDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {

		UserDTO dto = userService.getUserById(id);

		return new ResponseEntity<UserDTO>(dto, new HttpHeaders(), HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO User) {
		UserDTO dto = userService.createUser(User);
		return new ResponseEntity<UserDTO>(dto, new HttpHeaders(), HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id,@Valid @RequestBody UserDTO User) {
		UserDTO dto = userService.updateUser(id, User);
		return new ResponseEntity<UserDTO>(dto, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id) {
		UserDTO dto = userService.deleteUser(id);
		return new ResponseEntity<UserDTO>(dto, new HttpHeaders(), HttpStatus.OK);
	}
	

}
