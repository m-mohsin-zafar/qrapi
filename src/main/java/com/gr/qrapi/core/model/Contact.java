package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private int accountId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String gender;
	private String phoneNumber;
	private String status;	
	private List<ContactAddress> contactAddresses;

	public Contact() {
		
	}

	public Contact(String firstName, String lastName, String emailAddress, String gender, String phoneNumber,
			String status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ContactAddress> getContactAddresses() {
		return contactAddresses;
	}

	public void setContactAddresses(List<ContactAddress> contactAddresses) {
		this.contactAddresses = contactAddresses;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", status=" + status + "]";
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
		
}
