package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Contact;

/**
 * @author Muhammad Mohsin
 */
public interface ContactDao extends GenericDao<Contact, Integer>{

	public Contact addContact(String firstName, String lastName, String emailAddress, String gender, String phoneNumber,
			String status, int accountId, String streetAddress, String city, String state, String country);
	public List<Contact> viewContacts();
	public Contact updateContact(int id, String firstName, String lastName, String emailAddress, String gender, String phoneNumber,
			String status, int accountId, String streetAddress, String city, String state, String country);
	public void deleteContact(int id);
}
