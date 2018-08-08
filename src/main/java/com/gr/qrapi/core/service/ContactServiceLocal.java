package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Contact;

/**
 * @author Muhammad Mohsin
 */
@Local
public interface ContactServiceLocal {

	List<Contact> getAllContacts();

	Contact addContact(Contact contact, int accountId);

	Contact updateContact(Contact contact, int id);
	
	List<Contact> getContactById (int accountId);

}
