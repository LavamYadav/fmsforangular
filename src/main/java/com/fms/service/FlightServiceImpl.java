package com.fms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.dto.Flight;
import com.fms.exception.FlightException;
import com.fms.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	//getting all flights record by using the method findaAll() of CrudRepository
	public List<Flight> getAllflights() throws FlightException {
		List<Flight> flights = new ArrayList<>();
		flightRepository.findAll().forEach(flight1 -> flights.add(flight1));
		if(flights.isEmpty()) {
			throw new FlightException("NO FLIGHT IS AVAILABLE");
		}
		return flights;
	}

	//getting a specific flight by using the method findById() of CrudRepository
	public Optional<Flight> getFlightsByFlightNumber(int flightNumber1) {

		return flightRepository.findById(flightNumber1);
	}

	//saving a specific flight by using the method save() of CrudRepository  
	public void saveOrUpdate(Flight flight) {
		flightRepository.save(flight);
	}

	//deleting a specific flight by using the method deleteById() of CrudRepository 
	public void delete(int flightNumber1) throws FlightException {
		
		Optional<Flight> removedFlight=flightRepository.findById(flightNumber1);
		
		  if(removedFlight==null) {
		  
		  throw new FlightException("FLIGHT DOESN'T EXISTS TO DELETE");
		  
		  }
		 
		
		 flightRepository.deleteById(flightNumber1);
		
	}

	//updating a flight
	public Flight modify(Flight flights) throws FlightException {
		Optional<Flight> flightToBeModified=flightRepository.findById(flights.getFlightNumber());
		
		if(flightToBeModified==null) {
			
			throw new FlightException("FLIGHT DOESN'T EXISTS TO MODIFY");
			
		}else
		return flightRepository.save(flights);
	}
}