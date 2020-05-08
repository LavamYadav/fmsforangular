package com.fms.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fms.dto.UserDTO;
import com.fms.entities.User;

/**
 * @author gauti
 *
 */
public interface UserRepository extends CrudRepository<User, BigInteger>{

	/**
	 * @param userEmail
	 * @return
	 * 
	 * find user by using email
	 */
	Optional<User> findByEmail(String userEmail);
	
	/**
	 * @param resetToken
	 * @return
	 * 
	 * finds user using token
	 */
	Optional<User> findByResetToken(String resetToken);
}
