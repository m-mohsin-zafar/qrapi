package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.DataList;
import com.gr.qrapi.core.model.QRUrl;

/**
 * @author Muhammad Mohsin 
 */
@Local
public interface UrlShortnerServiceLocal {

	QRUrl addShortUrl (QRUrl qrurl);
	List<QRUrl> getAllUrlsDetail ();
	QRUrl getDetailsById (int id);
	int getGrandSumById (int id);
	DataList getPlatformClicks (int id);
	DataList getBrowserClicks (int id);
	DataList getByYearClicks (int id);
}
