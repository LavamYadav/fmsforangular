package com.fms.service;

import java.util.List;
import java.util.Optional;

import com.fms.dto.Flight;
import com.fms.exception.FlightException;

public interface FlightService {
	
	public List<Flight> getAllflights() throws FlightException;
	
	public Optional<Flight> getFlightsByFlightNumber(int flightNumber1);
	
	public void saveOrUpdate(Flight flight);
	
	public void delete(int flightNumber1) throws FlightException;
	
	public Flight modify(Flight flights) throws FlightException;

}
