package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.model.ContactAddress;

/**
 * @author Muhammad Mohsin
 */
public interface ContactDao extends GenericDao<Contact, Integer>{

	public Contact addContact(int accountId, Contact contact, ContactAddress contactAddress);
	public List<Contact> viewContacts();
	public List<Contact> getContactsById (int AccountId);
	public Contact updateContact(int accountId, Contact contact, ContactAddress contactAddress);
	public void deleteContact(int id);
	
	
}
