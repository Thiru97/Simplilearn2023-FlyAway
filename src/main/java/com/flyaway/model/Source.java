package com.flyaway.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Source {

	@Column(name = "source_country")
	protected String sourceCountryName;

	@Column(name = "source_city")
	protected String sourceCityName;

	@Column(name = "source_airport")
	protected String sourceAirportName;

	public Source() {

	}

	public Source(String sourceCountryName, String sourceCityName, String sourceAirportName) {
		super();
		this.sourceCountryName = sourceCountryName;
		this.sourceCityName = sourceCityName;
		this.sourceAirportName = sourceAirportName;
	}

	public String getSourceCountryName() {
		return sourceCountryName;
	}

	public void setSourceCountryName(String sourceCountryName) {
		this.sourceCountryName = sourceCountryName;
	}

	public String getSourceCityName() {
		return sourceCityName;
	}

	public void setSourceCityName(String sourceCityName) {
		this.sourceCityName = sourceCityName;
	}

	public String getSourceAirportName() {
		return sourceAirportName;
	}

	public void setSourceAirportName(String sourceAirportName) {
		this.sourceAirportName = sourceAirportName;

	}

	@Override
	public String toString() {
		return "Source [sourceCountryName=" + sourceCountryName + ", sourceCityName=" + sourceCityName
				+ ", sourceAirportName=" + sourceAirportName + ", getSourceCountryName()=" + getSourceCountryName()
				+ ", getSourceCityName()=" + getSourceCityName() + ", getSourceAirportName()=" + getSourceAirportName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
