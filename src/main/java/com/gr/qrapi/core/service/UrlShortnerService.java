package com.gr.qrapi.core.service;

import javax.ejb.Stateless;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

		qrurl.setShortUrl(generateShortUrl(qrurl.getOriginalUrl()));
		
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

	private String generateShortUrl(String original_url) {

		String short_url = null;
		String[] parts = original_url.split("[.]");

		if (original_url != null) {
			if(parts[2].contains("/")) {
				String[] subPart = parts[2].split("[/]");
				short_url = parts[0]+"." + parts[1].substring(0, (parts[1].length() / 3))
						+ subPart[1].substring(((subPart[1].length() / 3) * 2)) + parts[1].length() + "." + subPart[0];
			}else {
				short_url = parts[0]+"." + parts[1].substring(0, (parts[1].length() / 3))
						+ parts[1].substring(((parts[1].length() / 3) * 2)) + parts[1].length() + "." + parts[2];
			}
			
		}

		return short_url;
	}
	
}
