package com.evalent.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalent.constant.ErrorConstant;
import com.evalent.dto.UserDTO;
import com.evalent.exception.CustomBussinessException;
import com.evalent.exception.EmptyInputException;
import com.evalent.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@GetMapping("/getUserList")
	public List<UserDTO> getAllUsers() {

		log.info("Fetching User List Type for User DTO starting  at " + new Date());
		return userService.getAllUsers();

	}

	// REST request handling method to add a new user
	@PostMapping("/addUser")
	public UserDTO addUserDetails(@RequestBody UserDTO userDTO) {

		log.info("Adding User Type for User DTO starting at " + new Date());
		return userService.addUserDetails(userDTO);
	}

	// REST request handling method to delete user details
	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUserDetails(@PathVariable Integer userId) {
		log.info("Deletion of User starting and User Id {}", userId + " at " + new Date());
		try {
			userService.deleteUserDetails(userId);
		} catch (RuntimeException e) {

			throw new CustomBussinessException("Employee Deletion is failed");
		}
	}

	// REST request to get user details of specific id
	@GetMapping("/getUser/{userId}")
	public UserDTO getUserDetails(@PathVariable Integer userId) {
		log.info("Getting User details starting and User Id {}", userId + " at " + new Date());
		return userService.getUserDetails(userId);
	}

	// Add REST request handling method to update user details
	@PutMapping("/updateUser")
	public UserDTO updateUserDetails(@RequestBody UserDTO userDTO) {
		log.info("Updating User details starting and User Id {}", userDTO.getId() + " at " + new Date());
		
		if(userDTO.getId() == null) {
			throw new EmptyInputException("400",ErrorConstant.USER_FOUND);
		}
		return userService.updateUserDetails(userDTO);
	}

}
