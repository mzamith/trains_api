package org.feup.trains.service;

import java.util.List;

import org.feup.trains.model.TravelDTO;

public interface TripService {
	
	List<TravelDTO> findTravelBetweenStations(Long originStationId, Long destinationStationId);

}
