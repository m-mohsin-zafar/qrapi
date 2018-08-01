package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;

/**
 * @author Muhammad Mohsin
 */
public class AccountDaoHibernateImpl extends AbstractHibernateDao<Account, Integer> implements AccountDao {

	public static AccountDao getDao() {
		return DaoManager.getInstance().getDao(AccountDao.class);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Account> getAllAccounts() {
//		
//		try {
//			Session session = getSession();
//			Criteria criteria = session.createCriteria(Account.class);
//			criteria.setMaxResults(100);
//
//			List<Account> accounts = (List<Account>) criteria.list();
//
//			return accounts;
//		} catch (Exception aex) {
//			throw new DaoException(aex);
//		}
//	}

	@Override
	public Account addAccount(String name, String emailAddress, String timeZone) {

		Account account = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			if (name != null && emailAddress != null && timeZone != null) {

				if (!name.isEmpty() && !emailAddress.isEmpty() && !timeZone.isEmpty()) {

					account = new Account();
					account.setName(name);
					account.setEmailAddress(emailAddress);
					account.setTimeZone(timeZone);

					tx = session.beginTransaction();
					session.save(account);
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
		return account;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> viewAllAccounts() {

		Transaction tx = null;
		Session session = getSession();

		List<Account> accounts = null;

		try {

			tx = session.beginTransaction();

			accounts = session.createQuery("From Account").list();

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			closeSession(session);
		}

		return accounts;
	}

	@Override
	public Account updateAccount(int id, String name, String emailAddress, String timeZone) {

		Account account = null;

		Transaction tx = null;
		Session session = getSession();

		try {

			tx = session.beginTransaction();

			account = (Account) session.get(Account.class, id);
			Account original = account;

			if (account != null) {

				if (name != null && emailAddress != null && timeZone != null) {

					if (!name.isEmpty()) {
						account.setName(name);
					} else {
						account.setName(original.getName());
					}

					if (!emailAddress.isEmpty()) {
						account.setEmailAddress(emailAddress);
					} else {
						account.setEmailAddress(original.getEmailAddress());
					}

					if (!timeZone.isEmpty()) {
						account.setTimeZone(timeZone);
					} else {
						account.setTimeZone(original.getTimeZone());
					}

					session.update(account);
					tx.commit();
				}
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// Need to see if this works or not. If not need to shift below finally.
			if (account != null) {
				account = (Account) session.get(Account.class, id);
			}
			closeSession(session);
		}

		return account;
	}

	@Override
	public void deleteAccount(int id) {

		Transaction tx = null;
		Session session = getSession();

		try {

			tx = session.beginTransaction();

			Account account = (Account) session.get(Account.class, id);

			if (account != null) {
				List<Contact> contacts = account.getContacts();
				List<AlertProfile> alertProfiles = account.getAlertProfiles();
				if (contacts != null) {
					ContactDao manageContact = ContactDaoHibernateImpl.getDao();
					for (Contact cont : contacts) {
						manageContact.deleteContact(cont.getId());
					}
				}
				if (alertProfiles != null) {
					AlertProfileDao manageAlertProfile = AlertProfileDaoHibernateImpl.getDao();
					for (AlertProfile aProfile : alertProfiles) {
						manageAlertProfile.deleteAlertProfile(aProfile.getId());
					}
				}
				session.delete(account);
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
