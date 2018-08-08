package com.gr.qrapi.core.model;

import java.io.Serializable;

public class ContactAddress implements Serializable{


	private static final long serialVersionUID = 1L;

	private int id;
	private String streetAddress;
	private String city;
	private String state;
	private String country;

	public ContactAddress() {

	}

	public ContactAddress(String streetAddress, String city, String state, String country) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "ContactAddress [streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", country="
				+ country + "]";
	}

}
