package com.fms.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fms.dto.UserDTO;
import com.fms.entities.User;
import com.fms.exceptions.UserExistsException;
import com.fms.exceptions.UserNotFoundException;
import com.fms.repository.UserRepository;

/**
 * @author gauti
 *
 */
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImp() {
		super();
	}

	@Override
	public UserDTO login(String email, String password) throws UserNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		} else if (!bCryptPasswordEncoder.matches(password , user.get().getUserPassword())) {
			return null;
		} else {
			user.get().setActive(true);
			userRepository.save(user.get());
			return new UserDTO(user.get().getUserId(), user.get().getUserName(), user.get().getUserPhone(),
					user.get().getEmail(), user.get().getUserType());
		}
	}

	@Override
	public Boolean register(User user) throws UserExistsException {
		Optional<User> tempUser = userRepository.findByEmail(user.getEmail());
		if (tempUser.isPresent()) {
			throw new UserExistsException("Email already present");
		}
		return userRepository.save(user) != null;
	}

	@Override
	public Boolean logout(BigInteger id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		} else {
			user.get().setActive(false);
			return userRepository.save(user.get()) != null;
		}
	}

	@Override
	public User forgotPassword(String email) throws UserNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return user.get();
	}

	@Override
	public Optional<User> findUserByResetToken(String resetToken) throws UserNotFoundException{
		Optional<User> user  = userRepository.findByResetToken(resetToken);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not foung");
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}
}
