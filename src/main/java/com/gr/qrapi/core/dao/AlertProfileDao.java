package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.AlertProfile;

/**
 * @author Muhammad Mohsin
 */
public interface AlertProfileDao extends GenericDao<AlertProfile, Integer>{

	public AlertProfile addAlertProfile(String name, int accountId);
	public List<AlertProfile> viewAllAlertProfiles();
	public AlertProfile updateAlertProfile(int id, String name, int accountId);
	public void deleteAlertProfile(int id);
	public List<AlertProfile> findByCitynCountry(int accountId, String city, String country);
}
