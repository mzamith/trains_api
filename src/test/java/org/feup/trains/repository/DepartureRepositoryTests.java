package org.feup.trains.repository;

import java.util.Collection;

import org.feup.trains.AbstractTest;
import org.feup.trains.model.Configuration;
import org.feup.trains.model.Departure;
import org.feup.trains.model.Trip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit tests for the AccountRepository interface.
 * 
 * @author Manuel Zamith
 *
 */
@Transactional
public class DepartureRepositoryTests extends AbstractTest {

	@Autowired
	private DepartureRepository repository;
	
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private ConfigurationRepository confRepository;
	
	@Autowired TripRepository tripRepository;

	@Test
	public void testFindByOrigin() {

		Collection<Departure> departures = repository.findByOrigin(1L);
		
		Collection<Trip> trips = tripRepository.findBackwardsTrips(6L, 2L);
		
		Configuration conf = confRepository.findByKey("trains-cents-per-kilometre");
		int price = Integer.parseInt(conf.getValue());

		Assert.assertNotNull("failure - expected entity not null", departures);
	}

}