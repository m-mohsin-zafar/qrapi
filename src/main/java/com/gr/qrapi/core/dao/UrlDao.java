package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.DataList;
import com.gr.qrapi.core.model.QRUrl;

/**
 * @author mzafar
 *
 */
public interface UrlDao extends GenericDao<QRUrl, Integer> {

	public QRUrl addShortUrl(QRUrl qrUrl);
	public QRUrl updateShortUrl(QRUrl qrUrl, int id);
	public List<QRUrl> viewAllUrls();
	public QRUrl searchByShortUrl(String shorturl);
	public QRUrl searchById(int id);
	public int getTotalClicks(int id);
	public DataList getPlatformClicks(int id);
	public DataList getBrowserClicks(int id);
	public DataList getByYearClicks(int id);
}
