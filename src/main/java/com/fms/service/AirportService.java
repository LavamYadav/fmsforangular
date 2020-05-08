package com.fms.service;

import java.util.List;

import com.fms.dto.Airport;
import com.fms.exception.InvalidAirportException;

public interface AirportService {
	
	public abstract List<Airport> getAllAirports();
	
	public abstract Airport getAirportById(String airportCode) throws InvalidAirportException;

}
