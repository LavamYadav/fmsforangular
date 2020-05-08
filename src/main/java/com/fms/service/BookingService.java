package com.fms.service;

import java.util.List;

import com.fms.dto.Booking;
import com.fms.dto.Passenger;

public interface BookingService {
	
		
		public abstract Booking addBooking(Booking booking);
		
		public abstract Booking modifyBooking(Booking booking);
		
		public abstract Booking viewBooking(int bookingId);
		
		public abstract List<Booking> viewBookingbyUserId(int userId);
		
		public abstract void deleteBooking(int bookingId);
		
		public abstract void validateBooking(Booking booking);
		
		public abstract void validateBooking(Passenger passenger);
		
		

}
