package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.common.exception.DaoException;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.AlertLocation;

/**
 * @author Muhammad Mohsin
 */
public class AlertProfileDaoHibernateImpl extends AbstractHibernateDao<AlertProfile, Integer>
		implements AlertProfileDao {

	public static AlertProfileDao getDao() {
		return DaoManager.getInstance().getDao(AlertProfileDao.class);
	}

	@Override
	public AlertProfile addAlertProfile(String name, int accountId) {

		AlertProfile alertProfile = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			Account account = (Account) session.get(Account.class, accountId);

			if (account != null) {

				if (!name.isEmpty()) {

					alertProfile = new AlertProfile();
					alertProfile.setName(name);
					
					List<AlertProfile> alertProfiles = account.getAlertProfiles();
					alertProfiles.add(alertProfile);
					account.setAlertProfiles(alertProfiles);

					tx = session.beginTransaction();
					session.save(alertProfile);
					session.update(account);
					tx.commit();

				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		}
		return alertProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlertProfile> viewAllAlertProfiles() {

		List<AlertProfile> alertProfiles = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			alertProfiles = session.createQuery("From AlertProfile").list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		} 
		return alertProfiles;
	}

	@Override
	public AlertProfile updateAlertProfile(int id, String name, int accountId) {
		AlertProfile alertProfile = null;

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			alertProfile = (AlertProfile) session.get(AlertProfile.class, id);
			AlertProfile original = alertProfile;
			Account account = (Account) session.get(Account.class, accountId);

			if (account != null) {

				if (name != null) {

					if (!name.isEmpty()) {
						alertProfile.setName(name);
					} else {
						alertProfile.setName(original.getName());
					}

					List<AlertProfile> alertProfiles = account.getAlertProfiles();
					alertProfiles.add(alertProfile);
					account.setAlertProfiles(alertProfiles);

					session.update(alertProfile);
					session.update(account);
					tx.commit();
				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		} 
		if (alertProfile != null) {
			alertProfile = (AlertProfile) session.get(AlertProfile.class, id);
		}

		return alertProfile;
	}

	@Override
	public void deleteAlertProfile(int id) {

		Session session = getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			AlertProfile alertProfile = (AlertProfile) session.get(AlertProfile.class, id);

			if (alertProfile != null) {

				List<AlertLocation> alertLocations = alertProfile.getLocations();

				if (alertLocations != null) {
					AlertLocationDao manageALocation = AlertLocationDaoHibernateImpl.getDao();
					for (AlertLocation aLocation : alertLocations) {
						manageALocation.deleteAlertLocation(aLocation.getId());
					}
				}

				session.delete(alertProfile);
				tx.commit();
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		}
	}

	@Override
	public List<AlertProfile> findByCitynCountry(int accountId, String city, String country) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<AlertProfile> findByCitynCountry(int accountId, String city, String country) {
//
//		List<AlertProfile> alertProfiles = null;
//
//		Session session = getSession();
//		Transaction tx = null;
//
//		try {
//
//			tx = session.beginTransaction();
//
////			CriteriaBuilder builder = tc.getSessionObject().getCriteriaBuilder();
////			CriteriaQuery<AlertProfile> criteria = builder.createQuery(AlertProfile.class);
////			Root<AlertProfile> aProfileRoot = criteria.from(AlertProfile.class);
////			criteria.select(aProfileRoot);
////			criteria.where(
////					builder.equal(aProfileRoot.ge, y))
//
//			Criteria criteria = session.createCriteria(AlertProfile.class).createAlias("account", "accountAlias")
//					.createAlias("locations", "alertLocs");
//
//			Criterion restCity = Restrictions.eq("alertLocs.city", city);
//			Criterion restCountry = Restrictions.eq("alertLocs.country", country);
//			Criterion restAccountId = Restrictions.eq("accountAlias.id", accountId);
//
//			// Criterion may be replaced by SimpleExpression, I guess....
//			criteria.add(Restrictions.and(restAccountId, Restrictions.or(restCity, restCountry)));
//
//			alertProfiles = criteria.list();
//			tx.commit();
//
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//			throw new DaoException(e);
//		} 
//		return alertProfiles;
//	}

}
