package com.gr.qrapi.core.model;

import java.io.Serializable;

public class Browsers implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int urlAnalyticsId;
	private int ieClicks;
	private int chromeclicks;
	private int safariClicks;
	private int fireforxClicks;
	private int othersClicks;
	
	public Browsers () {
		
	}

	public Browsers(int id, int urlAnalyticsId, int ieClicks, int chromeclicks, int safariClicks, int fireforxClicks,
			int othersClicks) {
		
		this.id = id;
		this.urlAnalyticsId = urlAnalyticsId;
		this.ieClicks = ieClicks;
		this.chromeclicks = chromeclicks;
		this.safariClicks = safariClicks;
		this.fireforxClicks = fireforxClicks;
		this.othersClicks = othersClicks;
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

	public int getIeClicks() {
		return ieClicks;
	}

	public void setIeClicks(int ieClicks) {
		this.ieClicks = ieClicks;
	}

	public int getChromeclicks() {
		return chromeclicks;
	}

	public void setChromeclicks(int chromeclicks) {
		this.chromeclicks = chromeclicks;
	}

	public int getSafariClicks() {
		return safariClicks;
	}

	public void setSafariClicks(int safariClicks) {
		this.safariClicks = safariClicks;
	}

	public int getFireforxClicks() {
		return fireforxClicks;
	}

	public void setFireforxClicks(int fireforxClicks) {
		this.fireforxClicks = fireforxClicks;
	}

	public int getOthersClicks() {
		return othersClicks;
	}

	public void setOthersClicks(int othersClicks) {
		this.othersClicks = othersClicks;
	}
	
	

}
