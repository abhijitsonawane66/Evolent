package com.evalent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evalent.entity.User;




public interface UserRepository extends JpaRepository<User, Integer>{

	
	@Query("select u from User u where u.firstName = :firstName and u.lastName = :lastName and u.status = :status")
	User getUserByName(@Param("firstName") String firstName,@Param("lastName") String lastName, @Param("status") boolean status);


}
