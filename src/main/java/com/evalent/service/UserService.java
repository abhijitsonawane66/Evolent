package com.evalent.service;

import java.util.List;

import com.evalent.dto.UserDTO;
import com.evalent.exception.CustomBussinessException;

public interface UserService {
	List<UserDTO> getAllUsers();
	UserDTO addUserDetails(UserDTO user) throws CustomBussinessException;
	void deleteUserDetails(Integer userId);
	public UserDTO getUserDetails(Integer userId);
	public UserDTO updateUserDetails(UserDTO user);
}
