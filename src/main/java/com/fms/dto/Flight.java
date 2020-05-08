package com.fms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author: Lavam
 * description: Flight Model
 * 
 */


@Entity(name="Flight")
@Table
public class Flight {

	@Id
	@NotEmpty(message = "Flight_Number cannot be blank")
	@Column(name = "flight_number")
	private int flightNumber;

	@NotEmpty
	@Size(min=2, message="Model should have atleast 2 characters")
	@Column(name = "flight_model")
	private String flightModel;

	@NotEmpty
	@Size(min=2, message="Carrier Name should have atleast 2 characters")
	@Column(name = "carrier_name")
	private String carrierName;

	@NotEmpty(message = "Seat cannot be blank")
	@Column(name = "seat_capacity")
	private int seatCapacity;

	
	public Flight() {
		super();

	}

	public int getFlightNumber() {
		return flightNumber;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", flightModel=" + flightModel + ", carrierName=" + carrierName
				+ ", seatCapacity=" + seatCapacity + "]";
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightModel() {
		return flightModel;
	}

	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
}
