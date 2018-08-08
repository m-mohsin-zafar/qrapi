package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.common.exception.DaoException;
import com.gr.qrapi.core.model.AlertLocation;
import com.gr.qrapi.core.model.AlertProfile;

/**
 * @author Muhammad Mohsin
 */
public class AlertLocationDaoHibernateImpl extends AbstractHibernateDao<AlertLocation, Integer> implements AlertLocationDao {

	public static AlertLocationDao getDao() {
		return DaoManager.getInstance().getDao(AlertLocation.class);
	}

	@Override
	public AlertLocation addAlertLocation(String city, String country, int alertProfileId) {

		AlertLocation alertLocation = null;
		
		Session session = getSession();
		Transaction tx = null;

		try {

			AlertProfile alertProfile = (AlertProfile) session.get(AlertProfile.class, alertProfileId);

			if (alertProfile != null) {

				if (!city.isEmpty() && !country.isEmpty()) {

					alertLocation = new AlertLocation();
					alertLocation.setCity(city);
					alertLocation.setCountry(country);
					
					List<AlertLocation> alertLocations = alertProfile.getLocations();
					alertLocations.add(alertLocation);
					alertProfile.setLocations(alertLocations);

					tx = session.beginTransaction();
					session.save(alertLocation);
					session.update(alertProfile);
					tx.commit();

				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		} 
		return alertLocation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlertLocation> viewAllAlertLocations() {

		List<AlertLocation> alertLocations = null;

		Session session = getSession();
		Transaction tx = null;
		
		try {

			tx = session.beginTransaction();

			alertLocations = session.createQuery("From AlertLocation").list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		} 
		return alertLocations;
	}

	@Override
	public AlertLocation updateAlertLocation(int id, String city, String country, int alertProfileId) {

		AlertLocation alertLocation = null;


		Session session = getSession();
		Transaction tx = null;
		
		try {

			tx = session.beginTransaction();

			AlertProfile alertProfile = (AlertProfile) session.get(AlertProfile.class, alertProfileId);

			alertLocation = (AlertLocation) session.get(AlertLocation.class, id);
			AlertLocation original = alertLocation;

			if (alertProfile != null && alertLocation != null) {

				if (city != null && country != null) {

					if (!city.isEmpty()) {
						alertLocation.setCity(city);
					} else {
						alertLocation.setCity(original.getCity());
					}
					if (!country.isEmpty()) {
						alertLocation.setCountry(country);
					} else {
						alertLocation.setCountry(original.getCountry());
					}

					List<AlertLocation> alertLocations = alertProfile.getLocations();
					alertLocations.add(alertLocation);
					alertProfile.setLocations(alertLocations);

					session.update(alertLocation);
					session.update(alertProfile);
					tx.commit();
				}
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		} 

		if (alertLocation != null) {
			alertLocation = (AlertLocation) session.get(AlertLocation.class, id);
		}

		return alertLocation;
	}

	@Override
	public void deleteAlertLocation(int id) {

		AlertLocation alertLocation = null;

		Session session = getSession();
		Transaction tx = null;
		
		try {

			tx = session.beginTransaction();

			alertLocation = (AlertLocation) session.get(AlertLocation.class, id);

			if (alertLocation != null) {

				session.delete(alertLocation);
				tx.commit();

			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(e);
		} 
	}
	
}
