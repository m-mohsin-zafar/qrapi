package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class UrlAnalytics implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int urlId;
	private int totalClicksCount;
	private Date yearOfClicks;
	private List<Platforms> platforms;
	private List<Browsers> browsers;
	
	public UrlAnalytics() {
		
	}

	public UrlAnalytics(int id, int urlId, int totalClicksCount, Date yearOfClicks) {
		
		this.id = id;
		this.urlId = urlId;
		this.totalClicksCount = totalClicksCount;
		this.yearOfClicks = yearOfClicks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public int getTotalClicksCount() {
		return totalClicksCount;
	}

	public void setTotalClicksCount(int totalClicksCount) {
		this.totalClicksCount = totalClicksCount;
	}

	public Date getYearOfClicks() {
		return yearOfClicks;
	}

	public void setYearOfClicks(Date yearOfClicks) {
		this.yearOfClicks = yearOfClicks;
	}

	public List<Platforms> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platforms> platforms) {
		this.platforms = platforms;
	}

	public List<Browsers> getBrowsers() {
		return browsers;
	}

	public void setBrowsers(List<Browsers> browsers) {
		this.browsers = browsers;
	}
	
	
	
}
