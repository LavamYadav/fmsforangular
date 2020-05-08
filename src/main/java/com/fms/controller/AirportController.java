package com.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Airport;
import com.fms.exception.InvalidAirportException;
import com.fms.service.AirportServiceImpl;

@RestController
public class AirportController {
	@Autowired
	AirportServiceImpl airportService;

   //creating a get mapping that retrieves all the books detail from the database 
	@GetMapping("/airport")
	private List<Airport> getAirports() {
		return airportService.getAllAirports();
	}

    //creating a get mapping that retrieves the detail of a specific book
	@GetMapping("/airport/{airportCode}")
	private Airport getAirports(@PathVariable("airportCode") String airportCode) throws InvalidAirportException {
		return airportService.getAirportById(airportCode);
	}
}
