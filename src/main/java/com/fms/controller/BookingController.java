package com.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Booking;
import com.fms.repository.BookingRepository;
import com.fms.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	BookingRepository bookingRepository;

	@GetMapping("/viewbooking/{userId}")
	public List<Booking> getBookingbyUserId(@PathVariable("userId") int userId) {
		return bookingService.viewBookingbyUserId(userId);
	}

	@GetMapping("/viewbook/{bookingId}")
	public Booking getBooking(@PathVariable("bookingId") int bookingId) {
		return bookingService.viewBooking(bookingId);
	}

	@PostMapping("/addbooking")
	public Booking saveBooking(@RequestBody Booking booking) {
		return bookingService.addBooking(booking);
	}

	@PutMapping("/modify/booking")
	public ResponseEntity<?> modifyBooking(@RequestBody Booking booking) {

		Booking bookingUpdate = new Booking();
		bookingUpdate = bookingRepository.findById(booking.getBookingId()).get();
		if (!bookingUpdate.isExpired())
			/* return bookingService.modifyBooking(booking); */
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);

		return new ResponseEntity<String>("Booking Expired", HttpStatus.OK);
	}

	@PutMapping("/cancelbooking/{bookingId}")
	public boolean cancelBooking(@PathVariable("bookingId") int bookingId) {
		bookingService.deleteBooking(bookingId);
		return true;
	}

}
