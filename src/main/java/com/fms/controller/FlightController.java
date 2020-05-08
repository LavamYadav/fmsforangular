package com.fms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Flight;
import com.fms.exception.FlightException;
import com.fms.service.FlightServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")  
public class FlightController {

	@Autowired
	FlightServiceImpl flightService;

    //creating a get mapping that retrieves all the flights detail from the database
	@GetMapping("/flight/view")
	public List<Flight> getAllFlights() throws FlightException {
		return flightService.getAllflights();
	}

    //creating a get mapping that retrieves the detail of a specific flight  
	@GetMapping("/flight/{flightNumber}")
	private Optional<Flight> getFlights(@PathVariable("flightNumber") int flightNumber) {
		return flightService.getFlightsByFlightNumber(flightNumber);
	}

    //creating a delete mapping that deletes a specified flight 
	@DeleteMapping("/flight/{flightNumber}")
	public void deleteFlight(@PathVariable("flightNumber") int flightNumber) throws FlightException {
		flightService.delete(flightNumber);
	}

    //creating post mapping that post the flight detail in the database 
	@PostMapping("/flight/add")
	public int saveFlight(@RequestBody Flight flights) {
		flightService.saveOrUpdate(flights);
		return flights.getFlightNumber();
	}

    //creating put mapping that updates the flight detail 
	@PutMapping("/flight/update")
	public Flight update(@RequestBody Flight flights) {
		flightService.saveOrUpdate(flights);
		return flights;
	}
}
