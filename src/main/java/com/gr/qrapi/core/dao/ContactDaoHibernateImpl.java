package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.common.exception.DaoException;
import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.ContactAddress;

/**
 * @author Muhammad Mohsin
 */
public class ContactDaoHibernateImpl extends AbstractHibernateDao<Contact, Integer> implements ContactDao {

	public static ContactDao getDao() {
		return DaoManager.getInstance().getDao(ContactDao.class);
	}

	@Override
	public Contact addContact(int accountId, Contact contact, ContactAddress contactAddress) {

		Session session = getSession();
		Transaction tx = null;

		try {

			Account account = (Account) session.get(Account.class, accountId);

			if (account != null) {

				if (!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contact.getEmailAddress().isEmpty() && !contact.getGender().isEmpty()
						&& !contact.getPhoneNumber().isEmpty() && !contact.getStatus().isEmpty()
						&& (contact.getStatus().equals("Active") || contact.getStatus().equals("InActive")) && !contactAddress.getStreetAddress().isEmpty()
						&& !contactAddress.getCity().isEmpty() && !contactAddress.getState().isEmpty() && !contactAddress.getCountry().isEmpty() ) {

					
					List<Contact> contacts = account.getContacts();
					contacts.add(contact);
					account.setContacts(contacts);
					
					List<ContactAddress> contactAddresses = contact.getContactAddresses();
					contactAddresses.add(contactAddress);
					contact.setContactAddresses(contactAddresses);

					tx = session.beginTransaction();
					
					session.saveOrUpdate(account);
					session.saveOrUpdate(contacts);
					session.saveOrUpdate(contactAddresses);

					tx.commit();

				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		}

		
		return contact;
	}

	
	/* Checked via POSTMAN */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> viewContacts() {

		List<Contact> contacts = null;

		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Contact.class);
			
			contacts = (List<Contact>) criteria.list();
			
		} catch (Exception e) {
			
			throw new DaoException(e);
		}
		return contacts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContactsById (int AccountId) {

		List<Contact> contacts = null;

		try {
			Session session = getSession();
			Account account = (Account) session.get(Account.class, AccountId);
			
			contacts = account.getContacts();
			
		} catch (Exception e) {
			
			throw new DaoException(e);
		}
		return contacts;
	}

	@Override
	public Contact updateContact(int accountId, Contact contact, ContactAddress contactAddress) {

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			Account account = (Account) session.get(Account.class, accountId);
			Contact original = (Contact) session.get(Contact.class, contact.getId());
			ContactAddress originalAddress = original.getContactAddresses().get(0);

			if (account != null) {
				if (contact != null) {

					if (contact.getFirstName().isEmpty()) {
						contact.setFirstName(original.getFirstName());
					} 
					if (contact.getLastName().isEmpty()) {
						contact.setLastName(original.getLastName());
					} 
					if (contact.getEmailAddress().isEmpty()) {
						contact.setEmailAddress(original.getEmailAddress());
					}
					if (contact.getGender().isEmpty()) {
						contact.setGender(original.getGender());
					}
					if (contact.getPhoneNumber().isEmpty()) {
						contact.setPhoneNumber(original.getPhoneNumber());
					} 
					if (contact.getStatus().isEmpty()) {
						contact.setStatus(original.getStatus());
					} 
					if (contactAddress.getStreetAddress().isEmpty()) {
						contactAddress.setStreetAddress(originalAddress.getStreetAddress());
					}
					if (contactAddress.getCity().isEmpty()) {
						contactAddress.setCity(originalAddress.getCity());
					}
					if (contactAddress.getState().isEmpty()) {
						contactAddress.setState(originalAddress.getState());
					}
					if (contactAddress.getCountry().isEmpty()) {
						contactAddress.setCountry(originalAddress.getCountry());
					}

					session.update(contact);
					session.update(contactAddress);

					tx.commit();

				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		}
		
		//Revisit this condition
//		if (contact != null) {
//			contact = (Contact) session.get(Contact.class, id);
//		}

		return contact;
	}

	@Override
	public void deleteContact(int id) {

		Contact contact = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, id);

			if (contact != null) {
				session.delete(contact);
				tx.commit();
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			closeSession(session);
		}

	}
	
	
	
	
	
	
/*	Commented Out
	contactAddress = contact.getContactAddress();
	if (contactAddress != null) {
		session.delete(contactAddress);
	}
	if (contact != null) {
			if (contact.getContactAddress() == null) {
				contact.setContactAddress(contactAddress);
			}
		}
		
*/

}
