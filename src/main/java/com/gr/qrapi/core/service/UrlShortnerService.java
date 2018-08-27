package com.gr.qrapi.core.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.UrlDaoHibernateImpl;
import com.gr.qrapi.core.model.DataList;
import com.gr.qrapi.core.model.QRUrl;

/**
 * @author Muhammad Mohsin Zafar
 */
@Stateless
public class UrlShortnerService implements UrlShortnerServiceLocal {

	
	public static UrlShortnerServiceLocal getService() {
		return (UrlShortnerServiceLocal) ServiceManager.getService(UrlShortnerServiceLocal.class);
	}

	@Override
	public QRUrl addShortUrl(QRUrl qrurl) {

		qrurl.setShortUrl(generateShortUrl());
		
		LocalDate today =LocalDate.now();
		qrurl.setDateCreated(Date.valueOf(today));
		qrurl.setExpiryDate(Date.valueOf(today.plusDays(1)));

		return UrlDaoHibernateImpl.getDao().addShortUrl(qrurl);
	}
	
	@Override
	public List<QRUrl> getAllUrlsDetail() {
		return UrlDaoHibernateImpl.getDao().viewAllUrls();
	}
	
	@Override
	public QRUrl getDetailsById(int id) {
		return UrlDaoHibernateImpl.getDao().searchById(id);
	}
	
	@Override
	public int getGrandSumById(int id) {
		return UrlDaoHibernateImpl.getDao().getTotalClicks(id);
	}

	@Override
	public DataList getPlatformClicks(int id) {
		return UrlDaoHibernateImpl.getDao().getPlatformClicks(id);
	}

	@Override
	public DataList getBrowserClicks(int id) {
		return UrlDaoHibernateImpl.getDao().getBrowserClicks(id);
	}

	@Override
	public DataList getByYearClicks(int id) {
		return UrlDaoHibernateImpl.getDao().getByYearClicks(id);
	}

	private String generateShortUrl(){

		String short_url = null;
		String prefix = "http://myapp/gr";
		
		SecureRandom crunchifyPRNG = null;
		try {
			crunchifyPRNG = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String randomNumber = new Integer(Math.abs(crunchifyPRNG.nextInt())).toString();
		
		short_url = prefix + randomNumber;

		return short_url;
	}
	
}
