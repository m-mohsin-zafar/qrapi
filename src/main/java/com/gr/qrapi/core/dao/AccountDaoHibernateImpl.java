package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.common.exception.DaoException;
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

	@Override
	public Account addAccount(Account account) {
		Transaction tx = null;
		try {
			
			if (account != null) {

				Session session = getSession();
				

				if (account.getName() != null && account.getEmailAddress() != null && account.getTimeZone() != null) {

					if (!account.getName().isEmpty() && !account.getEmailAddress().isEmpty()
							&& !account.getTimeZone().isEmpty()) {

						tx = session.beginTransaction();
						session.save(account);
						tx.commit();
					}

				}
			}
		} catch (Exception aex) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(aex);
		}
		return account;
	}

	/* Checked via POSTMAN */
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> viewAllAccounts() {

		List<Account> accounts = null;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Account.class);

			accounts = (List<Account>) criteria.list();

		} catch (Exception aex) {
			throw new DaoException(aex);
		}
		return accounts;
	}

	public Account getById(int id) {

		Account account = null;
		try {

			Session session = getSession();

			account = (Account) session.get(Account.class, id);

		} catch (Exception aex) {

			throw new DaoException(aex);
		}

		return account;

	}

	@Override
	public Account updateAccount(int id, Account account) {
		
		Session session = getSession();
		Transaction tx = null;
		try {

			

			tx = session.beginTransaction();

			Account original = (Account) session.get(Account.class, id);

			if (account != null && original != null) {

				if (account.getName() != null && account.getEmailAddress() != null && account.getTimeZone() != null) {

					if (account.getName().isEmpty()) {
						account.setName(original.getName());
					}
					if (account.getEmailAddress().isEmpty()) {
						account.setEmailAddress(original.getEmailAddress());
					}
					if (account.getTimeZone().isEmpty()) {
						account.setTimeZone(original.getTimeZone());
					}
					session.update(account);
					tx.commit();
				}
			}

		} catch (Exception aex) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(aex);
		}
		// Need to see if this works or not. If not need to shift below finally.
		if (account != null) {
			account = (Account) session.get(Account.class, id);
		}

		return account;
	}

	@Override
	public void deleteAccount(int id) {
		Session session = getSession();
		Transaction tx = null;
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
					AlertProfileDao manageAlertProfile = AlertProfileDaoHibernateImpl.getDao();
					for (AlertProfile alertProfile : alertProfiles) {
						manageAlertProfile.deleteAlertProfile(alertProfile.getId());
					}
				}

				session.delete(account);
				tx.commit();
			}

		} catch (Exception aex) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(aex);
		}
	}

	public Account searchByUsername(String username) {

		Account account = null;
		try {

			Session session = getSession();

			if (username != null) {
				Criteria criteria = session.createCriteria(Account.class);
				criteria.add(Restrictions.eq("userName", username));
				account = (Account) criteria.uniqueResult();
			}
		} catch (Exception aex) {

			throw new DaoException(aex);
		}

		return account;
		
	}
}
