package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.model.ContactAddress;

/**
 * @author Muhammad Mohsin
 */
@Local
public interface ContactServiceLocal {

	List<Contact> getAllContacts();

	Contact addContact(Contact contact, int accountId, ContactAddress contactAddress);

	Contact updateContact(Contact contact, int id);
	
	List<Contact> getContactsByAccountId (int accountId);
	
	Contact findContactById (int contactId);
	
	List<ContactAddress> getAddressesByAccount (int accountId);
	
	void deleteContact (int id);

}
