package org.feup.trains.dto;

import java.io.Serializable;
import java.util.List;

import org.feup.trains.model.Departure;
import org.feup.trains.model.Trip;

public class TimetableDTO implements Serializable {
	
	private List<Departure> departures;
	private List<Trip> trips;
	
	public List<Departure> getDepartures() {
		return departures;
	}
	public void setDepartures(List<Departure> departures) {
		this.departures = departures;
	}
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	
}
