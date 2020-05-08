package com.fms.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class ScheduleFlight {
	@Id
	@Column(name = "schedule_flight_id")
	private int scheduleFlightId;

	
	@OneToOne(fetch = FetchType.EAGER)
	private Flight flight;

	
	@Column(name = "available_seats")
	private int availableSeats;

	@OneToOne(cascade = CascadeType.ALL)
	private Schedule schedule;
	
	@NotEmpty(message = "Ticket cost cannot be blank")
	@Column(name = "ticket_cost")
	private Double ticketCost;

	@Column(name = "scheduleFlightState")
	private Boolean scheduleFlightState;

	public ScheduleFlight() {
		super();
		
	}

	public int getScheduleFlightId() {
		return scheduleFlightId;
	}

	public void setScheduleFlightId(int scheduleFlightId) {
		this.scheduleFlightId = scheduleFlightId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public Boolean getScheduleFlightState() {
		return scheduleFlightState;
	}

	public void setScheduleFlightState(Boolean scheduleFlightState) {
		this.scheduleFlightState = scheduleFlightState;
	}

}
