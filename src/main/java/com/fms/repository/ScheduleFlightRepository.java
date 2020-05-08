package com.fms.repository;

import org.springframework.data.repository.CrudRepository;

import com.fms.dto.Flight;
import com.fms.dto.ScheduleFlight;

public interface ScheduleFlightRepository extends CrudRepository<ScheduleFlight, Integer> {
	ScheduleFlight findByflight(Flight flight);
}
