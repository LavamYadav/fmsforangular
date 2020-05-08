package com.fms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.dto.Airport;
import com.fms.dto.ScheduleFlight;
import com.fms.repository.ScheduleFlightRepository;
import com.fms.repository.ScheduleRepository;

@Service
public class ScheduleFlightServiceImpl implements ScheduleFlightService {
		
		@Autowired
		ScheduleFlightRepository scheduleFlightRepository;
		
		@Autowired
		ScheduleRepository scheduleRepository;

		@Override
		public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
			
			return scheduleFlightRepository.save(scheduleflight);
		}

		@Override
		public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate) {
			List<ScheduleFlight> scheduleFlightList = new ArrayList<>();
			scheduleFlightRepository.findAll().forEach(ScheduleFlight1 -> scheduleFlightList.add(ScheduleFlight1));
			List<ScheduleFlight> extractedFlightList = new ArrayList<>();
			for (ScheduleFlight scheduleFlight : scheduleFlightList) {
				if (scheduleFlight.getSchedule().getSourceAirport().equals(source)
						&& scheduleFlight.getSchedule().getDestinationAirport().equals(destination)) {
					System.out.println("Schedule"+scheduleFlight.getSchedule().getDepartureDateTime().toLocalDate());
					System.out.println("User"+flightDate);
					if(scheduleFlight.getSchedule().getDepartureDateTime().toLocalDate().equals(flightDate))
						extractedFlightList.add(scheduleFlight);
				}
			}
			
			return extractedFlightList;
		}

		@Override
		public ScheduleFlight viewScheduleFlightsById(int flightId) {
			return scheduleFlightRepository.findById(flightId).get();
		}

		@Override
		public List<ScheduleFlight> viewScheduleFlight() {
			List<ScheduleFlight> scheduleFlightList = new ArrayList<>();
			scheduleFlightRepository.findAll().forEach(ScheduleFlight1 -> scheduleFlightList.add(ScheduleFlight1));
			return scheduleFlightList;
		}

		@Override
		public ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight) {
			// TODO Auto-generated method stub
			ScheduleFlight scheduleFlightUpdate = new ScheduleFlight();
			scheduleFlightUpdate = scheduleFlightRepository.findById(scheduleFlight.getScheduleFlightId()).get();
			scheduleFlightUpdate.setSchedule(scheduleFlight.getSchedule());
			scheduleFlightUpdate.setTicketCost(scheduleFlight.getTicketCost());
			scheduleFlightRepository.save(scheduleFlightUpdate);
			return scheduleFlightUpdate;
		}

		
		@Override
		public void deleteScheduleFlight(Integer flightId) {
			scheduleFlightRepository.deleteById(flightId);
		}

		@Override
		public void validateScheduledFlight(ScheduleFlight scheduleflight) {
			
			return;
		}

		


}
