package com.gr.qrapi.core.model;

import java.io.Serializable;

public class Platforms implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int urlAnalyticsId;
	private int windowsClicks;
	private int androidClicks;
	private int iphoneClicks;
	private int macClicks;
	private int x11Clicks;
	private int OtherClicks;
	
	public Platforms() {
		
	}

	public Platforms(int id, int urlAnalyticsId, int windowsClicks, int androidClicks, int iphoneClicks, int macClicks,
			int x11Clicks, int otherClicks) {
		
		this.id = id;
		this.urlAnalyticsId = urlAnalyticsId;
		this.windowsClicks = windowsClicks;
		this.androidClicks = androidClicks;
		this.iphoneClicks = iphoneClicks;
		this.macClicks = macClicks;
		this.x11Clicks = x11Clicks;
		OtherClicks = otherClicks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUrlAnalyticsId() {
		return urlAnalyticsId;
	}

	public void setUrlAnalyticsId(int urlAnalyticsId) {
		this.urlAnalyticsId = urlAnalyticsId;
	}

	public int getWindowsClicks() {
		return windowsClicks;
	}

	public void setWindowsClicks(int windowsClicks) {
		this.windowsClicks = windowsClicks;
	}

	public int getAndroidClicks() {
		return androidClicks;
	}

	public void setAndroidClicks(int androidClicks) {
		this.androidClicks = androidClicks;
	}

	public int getIphoneClicks() {
		return iphoneClicks;
	}

	public void setIphoneClicks(int iphoneClicks) {
		this.iphoneClicks = iphoneClicks;
	}

	public int getMacClicks() {
		return macClicks;
	}

	public void setMacClicks(int macClicks) {
		this.macClicks = macClicks;
	}

	public int getX11Clicks() {
		return x11Clicks;
	}

	public void setX11Clicks(int x11Clicks) {
		this.x11Clicks = x11Clicks;
	}

	public int getOtherClicks() {
		return OtherClicks;
	}

	public void setOtherClicks(int otherClicks) {
		OtherClicks = otherClicks;
	}
	
	
	
	

}
