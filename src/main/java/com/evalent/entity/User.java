package com.evalent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.evalent.dto.UserDTO;

@Entity
@Table(name = "user_info")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private Integer phoneNumber;

	@Column(name = "status")
	private Boolean status =true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserDTO convertToDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(getId());
		userDTO.setFirstName(getFirstName());
		userDTO.setLastName(getLastName());
		userDTO.setEmail(getEmail());
		userDTO.setPhoneNumber(getPhoneNumber());
		userDTO.setStatus(getStatus());
		return userDTO;
	}

	public UserDTO convertToDTOForActiveUser() {
		UserDTO userDTO = new UserDTO();
		if (getStatus()) {
			userDTO.setId(getId());
			userDTO.setFirstName(getFirstName());
			userDTO.setLastName(getLastName());
			userDTO.setEmail(getEmail());
			userDTO.setPhoneNumber(getPhoneNumber());
			
		}
		return userDTO;
	}

}
