package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
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

	@SuppressWarnings("null")
	@Override
	public Contact addContact(String firstName, String lastName, String emailAddress, String gender, String phoneNumber,
			String status, int accountId, String streetAddress, String city, String state, String country) {
		Contact contact = null;
		ContactAddress contactAddress = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			Account account = (Account) session.get(Account.class, accountId);

			if (account != null) {

				if (!firstName.isEmpty() && !lastName.isEmpty() && !emailAddress.isEmpty() && !gender.isEmpty()
						&& !phoneNumber.isEmpty() && !status.isEmpty()
						&& (status.equals("Active") || status.equals("InActive")) && !streetAddress.isEmpty()
						&& !city.isEmpty() && !state.isEmpty() && !country.isEmpty()) {

					contact = new Contact(firstName, lastName, emailAddress, gender, phoneNumber, status, account);
					contactAddress = new ContactAddress(streetAddress, city, state, country, contact);

					tx = session.beginTransaction();
					session.save(contact);
					session.save(contactAddress);
					tx.commit();

				}
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			closeSession(session);
		}

		
		if (contact != null) {
//			List<ContactAddress> cont = null;
			if (contact.getContactAddress() == null) {
//				cont.add(contactAddress);
				contact.setContactAddress(contactAddress);
			}
		}
		return contact;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> viewContacts() {

		Session session = getSession();
		Transaction tx = null;

		List<Contact> contacts = null;

		try {

			tx = session.beginTransaction();

			contacts = session.createQuery("From Contact").list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			closeSession(session);
		}

		return contacts;
	}

	@Override
	public Contact updateContact(int id, String firstName, String lastName, String emailAddress, String gender,
			String phoneNumber, String status, int accountId, String streetAddress, String city, String state,
			String country) {

		Contact contact = null;
		ContactAddress contactAddress = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, id);
			Contact original = contact;

			if (contact != null) {

				if (!firstName.isEmpty()) {
					contact.setFirstName(firstName);
				} else {
					contact.setFirstName(original.getFirstName());
				}

				if (!lastName.isEmpty()) {
					contact.setLastName(lastName);
				} else {
					contact.setLastName(original.getLastName());
				}

				if (!emailAddress.isEmpty()) {
					contact.setEmailAddress(emailAddress);
				} else {
					contact.setEmailAddress(original.getEmailAddress());
				}

				if (!gender.isEmpty()) {
					contact.setGender(gender);
				} else {
					contact.setGender(original.getGender());
				}

				if (!phoneNumber.isEmpty()) {
					contact.setPhoneNumber(phoneNumber);
				} else {
					contact.setPhoneNumber(original.getPhoneNumber());
				}

				if (!status.isEmpty()) {
					contact.setStatus(status);
				} else {
					contact.setStatus(original.getStatus());
				}

				//Please Revisit this condition
				Account account = (Account) session.get(Account.class, accountId);
				if (account != null) {
					contact.setAccount(account);
				} else {
					contact.setAccount(null);
				}

//				contactAddress = contact.getContactAddress().get(0);
				contactAddress = contact.getContactAddress();

				if (!streetAddress.isEmpty()) {
					contactAddress.setStreetAddress(streetAddress);
				} else {
//					contactAddress.setStreetAddress(original.getContactAddress().get(0).getStreetAddress());
					contactAddress.setCountry(original.getContactAddress().getStreetAddress());
				}

				if (!city.isEmpty()) {
					contactAddress.setCity(city);
				} else {
//					contactAddress.setCity(original.getContactAddress().get(0).getCity());
					contactAddress.setCountry(original.getContactAddress().getCity());
				}

				if (!state.isEmpty()) {
					contactAddress.setState(state);
				} else {
//					contactAddress.setState(original.getContactAddress().get(0).getState());
					contactAddress.setCountry(original.getContactAddress().getState());
				}

				if (!country.isEmpty()) {
					contactAddress.setCountry(country);
				} else {
//					contactAddress.setCountry(original.getContactAddress().get(0).getCountry());
					contactAddress.setCountry(original.getContactAddress().getCountry());
				}

				session.update(contact);
				session.update(contactAddress);

				tx.commit();

			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			if (contact != null) {
				contact = (Contact) session.get(Contact.class, id);
			}
			closeSession(session);
		}

		
		return contact;
	}

	@Override
	public void deleteContact(int id) {

		Contact contact = null;
		ContactAddress contactAddress = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, id);

			if (contact != null) {
//				contactAddress = contact.getContactAddress().get(0);
				contactAddress = contact.getContactAddress();
				if (contactAddress != null) {
					session.delete(contactAddress);
				}
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

}
