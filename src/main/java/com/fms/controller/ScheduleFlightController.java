package com.fms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Airport;
import com.fms.dto.ScheduleFlight;
import com.fms.service.ScheduleFlightService;

@RestController
public class ScheduleFlightController {

	@Autowired
	ScheduleFlightService scheduleFlightService;

	@PostMapping(value = "/addSchedule")
	public int addScheduleFlight(@RequestBody ScheduleFlight scheduleflight) {

		scheduleFlightService.addScheduleFlight(scheduleflight);
		return scheduleflight.getScheduleFlightId();
	}
	/*
	 * //for testing purpose
	 * 
	 * @GetMapping(value = "/showdata") public List<ScheduleFlight>
	 * viewScheduleFlights() { return scheduleFlightService.viewScheduleFlight(); }
	 */

	
	  @GetMapping(value = "/showdata") public List<ScheduleFlight>
	  viewScheduleFlights(@RequestBody Airport source, @RequestBody Airport destination,@RequestBody LocalDate flightDate) 
	  { 
		  return scheduleFlightService.viewScheduleFlights(source,destination,flightDate); }
	 

	@GetMapping(value = "/showdatabyid/{scheduleFlightId}")
	public ScheduleFlight viewScheduleFlights(@PathVariable("scheduleFlightId") int scheduleFlightId) {
		return scheduleFlightService.viewScheduleFlightsById(scheduleFlightId);
	}

	@DeleteMapping("/scheduleflight/{scheduleFlightId}")
	public void deleteScheduleFlightById(@PathVariable("scheduleFlightId") int scheduleFlightId) {
		scheduleFlightService.deleteScheduleFlight(scheduleFlightId);
	}

	@PutMapping("/modifyScheduleFlight")
	private ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight) {

		return scheduleFlightService.modifyScheduleFlight(scheduleFlight);
	}

}
