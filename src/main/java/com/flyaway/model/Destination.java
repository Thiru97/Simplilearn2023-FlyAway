package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Destination {
	@Column(name = "destination_country")
	protected String destinationCountryName;

	@Column(name = "destination_city")
	protected String destinationCityName;

	@Column(name = "destination_airport")
	protected String destinationAirportName;

	public String getDestinationCountryName() {
		return destinationCountryName;
	}

	public Destination() {

	}

	public Destination(String destinationCountryName, String destinationCityName, String destinationAirportName) {
		super();
		this.destinationCountryName = destinationCountryName;
		this.destinationCityName = destinationCityName;
		this.destinationAirportName = destinationAirportName;
	}

	public void setDestinationCountryName(String destinationCountryName) {
		this.destinationCountryName = destinationCountryName;
	}

	public String getDestinationCityName() {
		return destinationCityName;
	}

	public void setDestinationCityName(String destinationCityName) {
		this.destinationCityName = destinationCityName;
	}

	public String getDestinationAirportName() {
		return destinationAirportName;
	}

	public void setDestinationAirportName(String destinationAirportName) {
		this.destinationAirportName = destinationAirportName;
	}

	@Override
	public String toString() {
		return "Destination [destinationCountryName=" + destinationCountryName + ", destinationCityName="
				+ destinationCityName + ", destinationAirportName=" + destinationAirportName + "]";
	}
}
