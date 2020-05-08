package com.fms.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.ResponseDTO;
import com.fms.dto.UserDTO;
import com.fms.entities.User;
import com.fms.exceptions.UserExistsException;
import com.fms.exceptions.UserNotFoundException;
import com.fms.service.EmailService;
import com.fms.service.UserService;

/**
 * @author gauti
 * 
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * @param email
	 * @param password
	 * @return
	 * 
	 * Rest controller endpoint to handle login request
	 */
	@PostMapping("/login")
	public ResponseDTO<?> login(@RequestParam(name = "email") Optional<String> email,
			@RequestParam(name = "password") Optional<String> password) {

		// Validation to check if email or password is empty or not
		if (!email.isPresent() || email.get().isEmpty()) {
			return new ResponseDTO<>(false, "Email Missing", null);
		} else if (!password.isPresent() || password.get().isEmpty()) {
			return new ResponseDTO<>(false, "Password Missing", null);
		} else {

			// try catch block to handle exception in case user not present
			try {

				UserDTO userDTO = userService.login(email.get(), password.get());

				// to handle user is present and password doesnot matches
				if (userDTO != null) {
					return new ResponseDTO<>(true, "Success", userDTO);
				} else {
					return new ResponseDTO<>(true, "Password doesnot match", userDTO);

				}
			} catch (UserNotFoundException e) {
				return new ResponseDTO<>(false, e.getMessage(), null);
			}
		}

	}

	/**
	 * @param userDTO
	 * @return
	 * 
	 * Rest controller endpoint to handle register request
	 */
	@PostMapping("/register")
	public ResponseDTO<Object> register(@Valid @RequestBody UserDTO userDTO) {

		// setting userDto to user entity to persist in db
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		user.setUserPassword(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
		user.setUserPhone(userDTO.getUserPhone());
		user.setUserType(userDTO.getUserType());

		// try catch block to handle user exits exception
		try {
			userService.register(user);
			return new ResponseDTO<>(true, "Successfully registered", null);
		} catch (UserExistsException e) {
			return new ResponseDTO<>(true, e.getMessage(), null);
		}
	}

	/**
	 * @param id
	 * @return
	 * 
	 * Rest controller endpoint to handle logout request
	 */
	@PostMapping("/logout")
	public ResponseDTO<Object> logout(@RequestParam(name = "id") Optional<BigInteger> id) {

		// Validation to check if id is present or not
		if (!id.isPresent() || id.get() == null) {
			return new ResponseDTO<>(false, "User id missing", null);
		}

		// try catch block to handle user not found execption
		try {
			userService.logout(id.get());
		} catch (UserNotFoundException e) {
			return new ResponseDTO<>(false, e.getMessage(), null);
		}

		return new ResponseDTO<>(false, "Successfully logged out", null);
	}

	/**
	 * @param email
	 * @param request
	 * @return
	 * 
	 * Rest controller endpoint to handle forgot request. It takes email as
	 * request parameter and sends token to email provided in order to
	 * initiate password change request
	 */
	@PostMapping("/forgotPassword")
	public ResponseDTO<Object> forgotPassword(@RequestParam(name = "email") Optional<String> email,
			HttpServletRequest request) {

		// validation to check is email is not empty
		if (!email.isPresent()) {
			return new ResponseDTO<>(false, "Email missing", null);
		}

		// try catch block to handle email present or not in db
		try {

			User user = userService.forgotPassword(email.get());

			// generating random uuid
			user.setResetToken(UUID.randomUUID().toString());

			// save token to database
			userService.saveUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			// creating email message to be sent
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");

			// user email to which email is to be sent
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");

			// Url sent in mail which contains token generated
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ ":9090/auth/reset?token=" + user.getResetToken());

			// sending email
			emailService.sendEmail(passwordResetEmail);

			return new ResponseDTO<>(false, "Successfully sent mail", null);

		} catch (UserNotFoundException e) {
			return new ResponseDTO<>(false, e.getMessage(), null);
		}
	}

	/**
	 * @param token
	 * @param response
	 * @return
	 * Rest controller to handle reset request. It takes token as request
	 * param and redirects the user to update password page
	 */
	@GetMapping("/reset")
	public ResponseDTO<Object> reset(@RequestParam("token") Optional<String> token, HttpServletResponse response) {

		try {
			// url redirects to update password page
			response.sendRedirect("update_page_url");
		} catch (IOException e) {
			return new ResponseDTO<>(false, "Falied to generate new password", null);
		}
		return new ResponseDTO<>(true, "Successfully generate new passowrd", null);
	}

	/**
	 * @param token
	 * @param password
	 * @return 
	 * 
	 * Rest controller to handle reset request. It takes token and updated
	 * password as request param and checks if user had initiated forget
	 * password request by checking for token in user reset token enitity if
	 * present updates the password
	 */
	@PostMapping("/reset")
	public ResponseDTO<Object> setNewPassword(@RequestParam(name = "token") Optional<String> token,
			@RequestParam(name = "password") Optional<String> password) {

		// validation to check token and email is present or not
		if (!token.isPresent() || !password.isPresent() || token.get().isEmpty() || password.get().isEmpty()) {
			return new ResponseDTO<>(false, "Falied to generate new password", null);
		} else {

			try {
				// find user by using token throws user not found exception if token is not present 
				Optional<User> user = userService.findUserByResetToken(token.get());
				user.get().setUserPassword(bCryptPasswordEncoder.encode(password.get()));
				user.get().setResetToken(null);
				userService.saveUser(user.get());
				return new ResponseDTO<>(true, "Successfully generate new passowrd", null);
				
			} catch (UserNotFoundException e) {
				return new ResponseDTO<>(false, "Falied to generate new password", null);
			}

		}
	}
}
