package com.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fms.dto.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

	List<Booking> findByUserId(int userId);
}
