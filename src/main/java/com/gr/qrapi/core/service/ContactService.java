package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.ContactDaoHibernateImpl;
import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.model.ContactAddress;

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
	public Contact addContact(Contact contact, int accountId, ContactAddress contactAddress) {
		return ContactDaoHibernateImpl.getDao().addContact(accountId, contact, contactAddress);
	}

	@Override
	public Contact updateContact(Contact contact, int id) {
		return contact;
	}
	
	@Override
	public List<Contact> getContactsByAccountId (int accountId){
		return ContactDaoHibernateImpl.getDao().getContactsById(accountId);
	}
	
	@Override
	public Contact findContactById (int contactId) {
		return ContactDaoHibernateImpl.getDao().findContact(contactId);
	}
	
	@Override
	public List<ContactAddress> getAddressesByAccount (int accountId){
		return ContactDaoHibernateImpl.getDao().getAddressByAccountId(accountId);
	}
	
	@Override
	public void deleteContact (int id) {
		ContactDaoHibernateImpl.getDao().deleteContact(id);
	}
}
