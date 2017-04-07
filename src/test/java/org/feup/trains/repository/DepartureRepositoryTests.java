package org.feup.trains.repository;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.feup.trains.AbstractTest;
import org.feup.trains.model.Configuration;
import org.feup.trains.model.Departure;
import org.feup.trains.model.Ticket;
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
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired TripRepository tripRepository;

	@Test
	public void testFindByOrigin() {

		Collection<Departure> departures = repository.findByOrigin(1L);
		
		Collection<Trip> trips = tripRepository.findBackwardsTrips(6L, 2L);
		
		Calendar after = Calendar.getInstance();
		Calendar before = Calendar.getInstance();
		
		after.set(2017, 3, 6, 23, 59, 59);
		before.set(2017, 3, 8, 0, 0, 0);
		
		Date after_date = after.getTime();
		Date before_date = before.getTime();
		
		List<Ticket> tickets = ticketRepository.findBetweenDates(after_date, before_date);
	//	List<Object[]> tickets2 = ticketRepository.findTicketsByStationForward("2011-04-07 00:00:00", "2019-04-07 00:00:00", "1");

		
		Configuration conf = confRepository.findByKey("trains-cents-per-kilometre");
		int price = Integer.parseInt(conf.getValue());

		Assert.assertNotNull("failure - expected entity not null", departures);
	}

}