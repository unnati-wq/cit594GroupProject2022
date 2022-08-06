package edu.upenn.cit594.util;

import java.util.ArrayList;

public class PropertiesData {
	
	private long marketValue;
	private long totalLiveableArea;
	private String zipCode;
	private long totalProperties= 0;
	private ArrayList <PropertiesData> propertyData = new ArrayList <PropertiesData> ();
	
	public PropertiesData (long marketValue, long totalLiveableArea, String zipCode) {
		this.marketValue=marketValue;
		this.totalLiveableArea=totalLiveableArea;
		this.zipCode=zipCode;
	}
	
	public ArrayList<PropertiesData> getdata(){
		return propertyData;
	}
	
	public void propertyCount(){
		totalProperties++;
	}

	public long getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(long marketValue) {
		this.marketValue += marketValue;
	}

	public long getTotalLiveableArea() {
		return totalLiveableArea;
	}

	public void setTotalLiveableArea(long totalLiveableArea) {
		this.totalLiveableArea += totalLiveableArea;
	}

	public String getZipCode() {
		return zipCode;
	}
	
	
}
