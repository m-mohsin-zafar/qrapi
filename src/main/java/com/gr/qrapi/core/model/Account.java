package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String emailAddress;
	private String timeZone;
	private String userName;
	private String passWord;
	private List<Contact> contacts;
	private List<AlertProfile> alertProfiles;
	
	public Account() {

	}
	
	public Account(String name, String emailAddress, String timeZone) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.timeZone = timeZone;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	} 

	public List<AlertProfile> getAlertProfiles() {
		return alertProfiles;
	}

	public void setAlertProfiles(List<AlertProfile> alertProfiles) {
		this.alertProfiles = alertProfiles;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", emailAddress=" + emailAddress + ", timeZone=" + timeZone + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
}
