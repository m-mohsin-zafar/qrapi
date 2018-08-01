package com.gr.qrapi.core.model;

import java.io.Serializable;

//@Entity
//@Table(name = "qr_alert_location")
public class AlertLocation implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private int id;
	
//	@Column(name = "city")
	private String city;
	
//	@Column(name = "country")
	private String country;
	
//	@ManyToOne
	private AlertProfile alertProfile;
	
	public AlertLocation() {
		
	}

	public AlertLocation(String city, String country, AlertProfile alertProfile) {
		this.city = city;
		this.country = country;
		this.alertProfile = alertProfile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AlertProfile getAlertProfile() {
		return alertProfile;
	}

	public void setAlertProfile(AlertProfile alertProfile) {
		this.alertProfile = alertProfile;
	}
	
	
	
	
}
