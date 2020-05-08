package com.fms.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.fms.dto.Booking;
import com.fms.dto.Passenger;
import com.fms.dto.ScheduleFlight;
import com.fms.repository.BookingRepository;
import com.fms.repository.ScheduleFlightRepository;

public class BookingServiceImpl implements BookingService {


	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ScheduleFlightRepository scheduleRepo;

	@Override
	public Booking addBooking(Booking booking) {
		ScheduleFlight scheduleFlight = new ScheduleFlight();
		scheduleFlight = scheduleRepo.findByflight(booking.getFlight());
		scheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats()-booking.getNoOfPassengers());
		scheduleRepo.save(scheduleFlight);
		bookingRepository.save(booking);
		return booking;
	}

	@Override
	public Booking modifyBooking(Booking booking) {
		// TODO Auto-generated method stub
		
		Booking bookingUpdate = new Booking();
		ScheduleFlight scheduleFlight = new ScheduleFlight();
		int difference;
		bookingUpdate = bookingRepository.findById(booking.getBookingId()).get();
		bookingUpdate.setCancelled(booking.isCancelled());
		difference = bookingUpdate.getNoOfPassengers() - booking.getNoOfPassengers();
		bookingUpdate.setNoOfPassengers(booking.getNoOfPassengers());
		scheduleFlight = scheduleRepo.findByflight(bookingUpdate.getFlight());
		scheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats() - difference);
		scheduleRepo.save(scheduleFlight);
		bookingRepository.save(bookingUpdate);
		return booking;
	}

	@Override
	public Booking viewBooking(int bookingId) {
		Booking booking;
		booking = bookingRepository.findById(bookingId).get();
		return booking;
	}

	@Override
	public List<Booking> viewBookingbyUserId(int userId) {
		// TODO Auto-generated method stub
		List<Booking> bookings;
		bookings = bookingRepository.findByUserId(userId);
		return bookings;
	}

	
	@Override
	public void deleteBooking(int bookingId) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(bookingId).get();
		booking.setCancelled(true);
		bookingRepository.save(booking);
	}

	@Override
	public void validateBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBooking(Passenger passenger) {
		// TODO Auto-generated method stub
		
	}

	
}
