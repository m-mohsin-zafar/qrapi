package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.AlertLocation;

/**
 * @author Muhammad Mohsin
 */
public interface AlertLocationDao extends GenericDao<AlertLocation, Integer> {

	public AlertLocation addAlertLocation (String city, String country, int alertProfileId);
	public List<AlertLocation> viewAllAlertLocations ();
	public AlertLocation updateAlertLocation (int id, String city, String country, int alertProfileId);
	public void deleteAlertLocation (int id);
}
