package com.gr.qrapi.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.common.exception.DaoException;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.Contact;
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

				if (!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty()
						&& !contact.getEmailAddress().isEmpty() && !contact.getGender().isEmpty()
						&& !contact.getPhoneNumber().isEmpty() && !contact.getStatus().isEmpty()
						&& (contact.getStatus().equals("Active") || contact.getStatus().equals("InActive"))
						&& !contactAddress.getStreetAddress().isEmpty() && !contactAddress.getCity().isEmpty()
						&& !contactAddress.getState().isEmpty() && !contactAddress.getCountry().isEmpty()) {

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
	public List<Contact> getContactsById(int AccountId) {

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
	public Contact findContact(int ContactId) {
		Contact contact = null;

		try {
			Session session = getSession();
			contact = (Contact) session.get(Contact.class, ContactId);

		} catch (Exception e) {

			throw new DaoException(e);
		}

		return contact;

	}

	@SuppressWarnings("null")
	@Override
	public List<ContactAddress> getAddressByAccountId(int AccountId) {

		List<ContactAddress> addresses = new ArrayList<>();
		try {

			List<Contact> contacts = getContactsById(AccountId);
			for (Contact con : contacts) {
				ContactAddress address = (ContactAddress) con.getContactAddresses().get(0);
				addresses.add(address);
			}

		} catch (Exception e) {

			throw new DaoException(e);
		}

		return addresses;

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

					Query query = session.createQuery("update qr_contact set first_name = :fName,"
							+ "last_name = :lName, email_address = :emailId, gender = :Gender, "
							+ "phone_number = :phoneNum, status = :Status where contact_id = " + original.getId());

					query.setParameter("fName", contact.getFirstName());
					query.setParameter("lName", contact.getLastName());
					query.setParameter("emailId", contact.getEmailAddress());
					query.setParameter("Gender", contact.getGender());
					query.setParameter("phoneNum", contact.getPhoneNumber());
					query.setParameter("Status", contact.getStatus());
					query.executeUpdate();

					Query query2 = session.createQuery("update qr_contact_address set street_address = :strAdr, "
							+ "city = :City, state = :State, country = :Country where address_id = "
							+ contactAddress.getId());

					query2.setParameter("strAdr", contactAddress.getStreetAddress());
					query2.setParameter("City", contactAddress.getCity());
					query2.setParameter("State", contactAddress.getState());
					query2.setParameter("Country", contactAddress.getCountry());
					query2.executeUpdate();

					tx.commit();

				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		}

		// Revisit this condition
//		if (contact != null) {
//			contact = (Contact) session.get(Contact.class, id);
//		}

		return contact;
	}

	@Override
	public void deleteContact(int id) {

		Contact contact = null;
		ContactAddress contactAddress = null;

		Session session = getSession();
		Transaction tx = null;
		Query query;

		try {

			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, id);

			if (contact != null) {
				contactAddress = (ContactAddress) contact.getContactAddresses().get(0);
				if (contactAddress != null) {
					query = session
							.createQuery("delete qr_contact_address where address_id = " + contactAddress.getId());
					query.executeUpdate();
				}
				query = session.createQuery("delete qr_contact where contact_id = " + contact.getId());
				query.executeUpdate();
				tx.commit();
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		}

	}

}
