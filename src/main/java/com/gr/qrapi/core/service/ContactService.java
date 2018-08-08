package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.ContactDaoHibernateImpl;
import com.gr.qrapi.core.model.Contact;

/**
 * @author Muhammad Mohsin Zafar
 */
@Stateless
public class ContactService implements ContactServiceLocal {

	public static ContactServiceLocal getService() {
		return (ContactServiceLocal) ServiceManager.getService(ContactServiceLocal.class);
	}

	@Override
	public List<Contact> getAllContacts() {
		return ContactDaoHibernateImpl.getDao().viewContacts();
	}
	
	@Override
	public Contact addContact(Contact contact, int accountId) {
		return contact;
	}

	@Override
	public Contact updateContact(Contact contact, int id) {
		return contact;
	}
	
	@Override
	public List<Contact> getContactById (int accountId){
		return ContactDaoHibernateImpl.getDao().getContactsById(accountId);
	}
}
