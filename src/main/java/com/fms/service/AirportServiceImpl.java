package com.fms.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.dto.Airport;
import com.fms.exception.InvalidAirportException;
import com.fms.repository.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService{
	@Autowired
	AirportRepository airportRepository;

    //getting all books record by using the method findaAll() of CrudRepository
	public List<Airport> getAllAirports() {
		List<Airport> airports = new ArrayList<>();
		airportRepository.findAll().forEach(airport1 -> airports.add(airport1));
		return airports;
	}

    //getting a specific record by using the method findById() of CrudRepository
	public Airport getAirportById(String airportCode) throws InvalidAirportException {
		Optional<Airport> airport = airportRepository.findById(airportCode);
		if (!airport.isPresent()) {
			throw new InvalidAirportException("Airport not found");
		}  else {
			return airport.get();
		}
		
		/*
		 * try { airportRepository.findById(airportCode).get(); }
		 * catch(NoSuchElementException exception) {
		 * 
		 * throw new InvalidAirportException("No Airport Found"); } return
		 * airportRepository.findById(airportCode).get();
		 */	
		}

}