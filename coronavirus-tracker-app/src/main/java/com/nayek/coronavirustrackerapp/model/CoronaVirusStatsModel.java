package com.nayek.coronavirustrackerapp.model;

public class CoronaVirusStatsModel {

	private String state;
	private String country;
	private String latitude;
	private String longitude;
	private String latestTotalCases;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(String latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	@Override
	public String toString() {
		return "CoronaVirusStatsModel [state=" + state + ", country=" + country + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", latestTotalCases=" + latestTotalCases + "]";
	}
}
