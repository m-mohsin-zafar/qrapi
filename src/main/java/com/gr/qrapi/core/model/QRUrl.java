package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class QRUrl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String originalUrl;
	private Date dateCreated;
	private Date expiryDate;
	private String shortUrl;
	private int grandSum;
	private List<UrlAnalytics> urlAnalytics;
	
	public QRUrl() {
		
	}
	
	public QRUrl(String originalUrl, Date dateCreated, Date expiryDate, String shortUrl) {
	
		this.originalUrl = originalUrl;
		this.dateCreated = dateCreated;
		this.expiryDate = expiryDate;
		this.shortUrl = shortUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public List<UrlAnalytics> getUrlAnalytics() {
		return urlAnalytics;
	}

	public void setUrlAnalytics(List<UrlAnalytics> urlAnalytics) {
		this.urlAnalytics = urlAnalytics;
	}

	public int getGrandSum() {
		return grandSum;
	}

	public void setGrandSum(int grandSum) {
		this.grandSum = grandSum;
	}
	
	
}
