package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

public class DataList implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<String> clickLabels;
	private List<Integer> clickValues;
	
	public DataList() {
		
	}
	public DataList(List<String> clickLabels, List<Integer> clickValues) {
		this.clickLabels = clickLabels;
		this.clickValues = clickValues;
	}
	public List<String> getClickLabels() {
		return clickLabels;
	}
	public void setClickLabels(List<String> clickLabels) {
		this.clickLabels = clickLabels;
	}
	public List<Integer> getClickValues() {
		return clickValues;
	}
	public void setClickValues(List<Integer> clickValues) {
		this.clickValues = clickValues;
	}
	
}
