package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

//@Entity
//@Table(name = "qr_alert_profile")
public class AlertProfile implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private int id;

//	@Column(name = "profile_name")
	private String name;

//	@ManyToOne
	private Account account;

//	@OneToMany(mappedBy = "alertProfile")
	private List<AlertLocation> locations;

	public AlertProfile() {

	}

	public AlertProfile(String name, Account account) {
		this.name = name;
		this.account = account;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<AlertLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<AlertLocation> locations) {
		this.locations = locations;
	}
	
	

}
