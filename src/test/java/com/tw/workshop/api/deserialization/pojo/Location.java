package com.tw.workshop.api.deserialization.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"postCode", "country", "countryAbbreviation", "places"})
public class Location {

	private String postCode;
	private String country;
	private String countryAbbreviation;
	private List<Places> places;

	@JsonProperty("post code")
	public String getPostCode() {
		return postCode;
	}
    
	@JsonProperty("post code")
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@JsonProperty("country abbreviation")
	public String getCountryAbbreviation() {
		return countryAbbreviation;
	}
    
	@JsonProperty("country abbreviation")
	public void setCountryAbbreviation(String countryAbbrevation) {
		this.countryAbbreviation = countryAbbrevation;
	}

	public List<Places> getPlaces() {
		return places;
	}

	public void setPlaces(List<Places> places) {
		this.places = places;
	}

}