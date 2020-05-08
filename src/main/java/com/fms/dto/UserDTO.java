package com.fms.dto;

import java.math.BigInteger;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author gauti
 *
 */
public class UserDTO {

	private BigInteger userId;

	@NotEmpty(message = "Name cannot be blank")
	@Size(min = 4, max = 24, message = "Name length should be between 4 to 24")
	private String userName;

	@NotEmpty(message = "Password cannot be blank")
	@Size(min = 8, max = 24, message = "Password length should be between 8 to 24")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;

	@NotEmpty(message = "Phone cannot be blank")
	@Pattern(regexp = "^[1-9][0-9]{9}$", message = "Invalid phone number should be 10digit and must not start with 0")
	private String userPhone;

	@NotEmpty(message = "Email cannot be blank")
	@Email(message = "Invalid email")
	private String email;

	@NotEmpty(message = "User type cannot be blank")
	private String userType;

	public UserDTO() {
		super();
	}
	
	public UserDTO(BigInteger userId,
			@NotEmpty(message = "Name cannot be blank") @Size(min = 4, max = 24, message = "Name length should be between 4 to 24") String userName,
			@NotEmpty(message = "Phone cannot be blank") @Pattern(regexp = "^[1-9][0-9]{9}$", message = "Invalid phone number should be 10digit and must not start with 0") String userPhone,
			@NotEmpty(message = "Email cannot be blank") @Email(message = "Invalid email") String email,
			@NotEmpty(message = "User type cannot be blank") String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.email = email;
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void String(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPhone=" + userPhone + ", email=" + email + ", userType=" + userType
				+ "]";
	}

}
