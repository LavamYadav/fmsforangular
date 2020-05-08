package com.fms.service;

import java.math.BigInteger;
import java.util.Optional;

import com.fms.dto.UserDTO;
import com.fms.entities.User;
import com.fms.exceptions.UserExistsException;
import com.fms.exceptions.UserNotFoundException;

/**
 * @author gauti
 *
 */
public interface UserService {

	public abstract UserDTO login(String email , String password) throws UserNotFoundException;
	
	public abstract Boolean logout(BigInteger id) throws UserNotFoundException;
	
	public abstract Boolean register(User user) throws UserExistsException;
	
	public abstract User forgotPassword(String email) throws UserNotFoundException;
	
    public abstract Optional<User> findUserByResetToken(String resetToken) throws UserNotFoundException;
    
    public abstract  void saveUser(User user);
}
