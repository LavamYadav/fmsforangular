package com.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fms.service.BookingService;
import com.fms.service.BookingServiceImpl;
import com.fms.service.EmailService;
import com.fms.service.EmailServiceImp;
import com.fms.service.UserService;
import com.fms.service.UserServiceImp;



@SpringBootApplication
public class FlightManagementSystem {
	
	public static void main(String[] args) {
		SpringApplication.run(FlightManagementSystem.class, args);
	}

	@Bean
	public BookingService getBookingService() {
		return new BookingServiceImpl();
	}

	@Bean
	public UserService getUserService() {
		return new UserServiceImp();
	}

	@Bean
	public EmailService getEmailService() {
		return new EmailServiceImp();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
