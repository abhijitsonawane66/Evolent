package com.evalent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evalent.constant.Constant;
import com.evalent.constant.ErrorConstant;
import com.evalent.dao.UserRepository;
import com.evalent.dto.UserDTO;
import com.evalent.entity.User;
import com.evalent.exception.CustomBussinessException;
import com.evalent.exception.EmptyInputException;

@Service
@Transactional
public class UserServiceimpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceimpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User u : userList) {
			userDTOs.add(u.convertToDTO());
		}
		log.info("Fetching User List Type for User DTO Completed  at " + new Date());
		return userDTOs;
	}

	
	@Override
	public UserDTO addUserDetails(UserDTO userDTO) {
		User user = new User();

		if(userDTO.getFirstName() == null || userDTO.getLastName() == null || userDTO.getFirstName().isEmpty() || userDTO.getLastName().isEmpty()) {
			throw new EmptyInputException("400",ErrorConstant.USER_FOUND);
		}
		User existingUser = userRepository.getUserByName(userDTO.getFirstName(), userDTO.getLastName(), true);

		if (existingUser == null) {
			BeanUtils.copyProperties(userDTO, user);

			User userNew = userRepository.save(user);
			log.info("Adding user details completed at "+new Date());
			return userNew.convertToDTO();
		} else {
			throw new CustomBussinessException(ErrorConstant.USER_FOUND);
		}
		
		

	}

	
	@Override
	public void deleteUserDetails(Integer userId) {

		User user = userRepository.getOne(userId);
		if(user != null) {
			user.setStatus(Constant.CONSTANT_FALSE);
			userRepository.save(user);
			log.info("Deletion of user is completed"+ new Date());
		}
		else
			throw new CustomBussinessException(ErrorConstant.USER_NOT_FOUND);
	}

	@Override
	public UserDTO getUserDetails(Integer userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		return optionalUser.isPresent() ? optionalUser.get().convertToDTO() : null;
	}

	
	@Override
	public UserDTO updateUserDetails(UserDTO userDTO) {

		
		User userDetails = userRepository.getOne(userDTO.getId());

		if (userDetails != null) {

			userDetails.setFirstName(userDTO.getFirstName());
			userDetails.setLastName(userDTO.getLastName());
			userDetails.setPhoneNumber(userDTO.getPhoneNumber());
			userDetails.setEmail(userDTO.getEmail());
			userRepository.saveAndFlush(userDetails);

		} else {
			throw new CustomBussinessException("User is not exist in system");
		}

		log.info("Updation of user details is completed"+ new Date());
		return userRepository.getOne(userDTO.getId()).convertToDTO();
	}

}
