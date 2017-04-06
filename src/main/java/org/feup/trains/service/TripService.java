package org.feup.trains.service;

import java.util.List;

import org.feup.trains.dto.TimetableDTO;
import org.feup.trains.dto.TravelDTO;

public interface TripService {
	
	List<TravelDTO> findTravelBetweenStations(Long originStationId, Long destinationStationId);
	
	TimetableDTO findForwardTimeline();
	
	TimetableDTO findBackwardsTimeline();

}
