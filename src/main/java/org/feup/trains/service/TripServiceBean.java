package org.feup.trains.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.feup.trains.model.Departure;
import org.feup.trains.model.SimpleTripDTO;
import org.feup.trains.model.TravelDTO;
import org.feup.trains.model.Trip;
import org.feup.trains.repository.DepartureRepository;
import org.feup.trains.repository.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class TripServiceBean implements TripService {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private TripRepository tripRepository;
    
    @Autowired
    private DepartureRepository departureRepository;
    
    @Autowired
    private ConfigurationService configurationService;

	@Override
	public List<TravelDTO> findTravelBetweenStations(Long originStationId, Long destinationStationId) {
		
		List<Departure> departures = new ArrayList<Departure>();
		List<Trip> trips = new ArrayList<Trip>();

		List<TravelDTO> travels = new ArrayList<TravelDTO>();
		
		if (originStationId >= destinationStationId){
			
			//Gotta find backwards trip
			trips = tripRepository.findBackwardsTrips(originStationId, destinationStationId);
			
			//TODO remove this hardcoded nonsense
			departures = departureRepository.findByOrigin(6L);
			
			
		} else {
			
			//Gotta find forward trip
			trips = tripRepository.findForwardTrips(originStationId, destinationStationId);
			
			//TODO remove this hardcoded nonsense
			departures = departureRepository.findByOrigin(1L);
			
		}
		
		
		for (Departure departure : departures){
			
			TravelDTO travel = new TravelDTO();
						
			travel.setStartTime(departure.getTime());			
			travel.setLine(departure.getLine());
			travel.setTrain(departure.getTrain());
			
			int totalDuration = 0;
			int totalDistance = 0;
			List<SimpleTripDTO> simpleTrips = new ArrayList<SimpleTripDTO>();
			
			for (Trip trip :  trips){
				simpleTrips.add(new SimpleTripDTO(new Date(departure.getTime().getTime() + totalDuration * 1000), trip.getFrom()));
				totalDuration += trip.getDuration();
				totalDistance += trip.getDistance();
			}
			
			travel.setTotalDistance(totalDistance);
			travel.setTotalDuration(totalDuration);
			travel.setTrips(simpleTrips);
			
			//include configuration here
			travel.setPrice(Math.round(totalDistance * configurationService.getPriceConfiguration() * 0.001));
			travel.setEndTime(new Date(departure.getTime().getTime() + totalDuration * 1000));
			travel.setFrom(trips.get(0).getFrom());
			travel.setTo(trips.get(trips.size() - 1).getTo());
			
			travels.add(travel);
			
		}
		
		return travels;
	}

}
