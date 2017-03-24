package org.feup.trains.model;

import java.util.Date;

public class SimpleTripDTO {
	
	private Date departureTime;
	private Station departureStation;
	
	public SimpleTripDTO(){}
	
	public SimpleTripDTO(Date departureTime, Station departureStation) {
		super();
		this.departureTime = departureTime;
		this.departureStation = departureStation;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public Station getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}
	
	

}
