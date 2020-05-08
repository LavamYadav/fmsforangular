package com.fms.service;

import java.time.LocalDate;
import java.util.List;

import com.fms.dto.Airport;
import com.fms.dto.ScheduleFlight;

public interface ScheduleFlightService {
	
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) ;
	
	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate);
    
	public ScheduleFlight viewScheduleFlightsById(int flightId) ;
    
	public List<ScheduleFlight > viewScheduleFlight();
    
	public ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight);
    
	public void deleteScheduleFlight(Integer flightId);

	void validateScheduledFlight(ScheduleFlight scheduleflight);

	

}
