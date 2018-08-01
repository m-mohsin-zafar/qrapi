package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

//@Entity
//@Table(name = "qr_contact")
public class Contact implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private int id;
	
//	@Column(name = "first_name", length=25)
	private String firstName;
	
//	@Column(name = "last_name", length=25)
	private String lastName;
	
//	@Column(name = "email_address",length=60)
	private String emailAddress;
	
//	@Column(name = "gender", length=6)
	private String gender;
	
	//Format = +923007612552
//	@Column(name = "phone_number", length=13)
	private String phoneNumber;
	
//	Format = Active or InAcitve
//	@Column(name = "status")
	private String status;	
	
//	@ManyToOne
	private Account account;
	
//	@OneToOne(mappedBy= "contact",fetch=FetchType.EAGER)
//	private List<ContactAddress> contactAddress;
	private ContactAddress contactAddress;
	
	public Contact() {
		
	}

	public Contact(String firstName, String lastName, String emailAddress, String gender, String phoneNumber,
			String status, Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.account = account;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
//	public List<ContactAddress> getContactAddress() 
	public ContactAddress getContactAddress(){
		return contactAddress;
	}

//	public void setContactAddress(List<ContactAddress> contactAddress)
	public void setContactAddress(ContactAddress contactAddress){
		this.contactAddress = contactAddress;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", status=" + status + "]";
	}
	
}
