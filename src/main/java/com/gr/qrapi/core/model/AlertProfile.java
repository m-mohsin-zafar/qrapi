package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class AlertProfile implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private List<AlertLocation> locations;

	public AlertProfile() {

	}

	public AlertProfile(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<AlertLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<AlertLocation> locations) {
		this.locations = locations;
	}
	
	

}
