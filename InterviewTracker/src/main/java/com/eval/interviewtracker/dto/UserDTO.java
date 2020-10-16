package com.eval.interviewtracker.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {
	
	private long id;
	
	@NotBlank(message = "{user.firstName}")
	@Size(min = 5, max = 30,message = "{user.firstName.invalid}")
	private String firstName;

	@NotBlank(message = "{user.lastName}")
	@Size(min = 3, max = 25,message = "{user.lastName.invalid}")
	private String lastName;

	@NotBlank(message = "{user.email}")
	@Email(message = "{user.email.invalid}")
	private String email;

	@NotBlank(message = "{user.mobile}")
	@Length(min = 10, max = 10,message = "{user.mobile.invalid}")
	private String mobile;
	
}