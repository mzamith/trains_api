/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DateFormatter;

import org.feup.trains.exception.InvalidCardException;
import org.feup.trains.exception.TrainCapacityExceededException;
import org.feup.trains.model.Account;
import org.feup.trains.model.Departure;
import org.feup.trains.model.Station;
import org.feup.trains.model.Ticket;
import org.feup.trains.model.TicketState;
import org.feup.trains.repository.AccountRepository;
import org.feup.trains.repository.DepartureRepository;
import org.feup.trains.repository.TicketRepository;
import org.feup.trains.security.jwt.TokenAuthenticationService;
import org.feup.trains.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Renato Ayres
 */
@org.springframework.stereotype.Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TicketServiceBean implements TicketService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final int BACKWARDS = 2;
	public static final int FORWARD = 1;

	/**
	 * The Spring Data repository for Ticket entities.
	 */
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	DepartureRepository departureRepository;

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ConfigurationService configurationService;

	@Override
	public Collection<Ticket> findAll() {
		logger.info("> findAll");

		return ticketRepository.findAll();
	}

	@Override
	public Collection<Ticket> findAllByDeparture(Long departure) {
		logger.info("> findAllByDeparture");

		return ticketRepository.findAllByDeparture(departure);
	}

	@Override
	public Ticket buyTicket(Ticket ticket, HttpServletRequest request) throws InvalidCardException, TrainCapacityExceededException {

		// Ensure the entity object to be created does NOT exist in the
		// repository. Prevent the default behavior of save() which will update
		// an existing entity if the entity matching the supplied id exists.
		if (ticket.getId() != null) {
			// Cannot create Ticket with specified ID value
			logger.error("Attempted to create a Exam, but id attribute was not null.");
			throw new EntityExistsException("The id attribute must be null to persist a new entity.");
		}
				
		if (this.isFullTrain(ticket)) throw new TrainCapacityExceededException("Train is already full");

		TokenAuthenticationService service = new TokenAuthenticationService();
		UserDetails user = service.getAuthenticatedUser(request);

		Account account = accountRepository.findByUsername(user.getUsername());

		if (!account.hasValidCard())
			throw new InvalidCardException("Card is expired or invalid");
		
		Date currentDate = new Date();

		logger.info("card is Valid. Saving ticket");
		ticket.setAccount(account);
		ticket.setOrdinal((int) Math.round(Math.random()));
		ticket.setLabel("dummy" + Math.random());
		ticket.setCode("dummy" + Math.random());
		ticket.setCreatedAt(currentDate);
		ticket.setEffectiveAt(currentDate);
		ticket.setState(TicketState.RESERVED);
		ticket.setExpiresAt(DateUtil.addDays(currentDate, configurationService.getExpirationDays()));

		Ticket savedTicket = ticketRepository.save(ticket);

		return savedTicket;

	}
	
	@Override
	public Collection<Ticket> findByAccount(HttpServletRequest request){
		
		TokenAuthenticationService service = new TokenAuthenticationService();
		UserDetails user = service.getAuthenticatedUser(request);
		
		return ticketRepository.findAllByUsername(user.getUsername());
		
	}
	
	public boolean isFullTrain(Ticket ticket){
		
		Date date = ticket.getDay();
		Station from = ticket.getFrom();
		Station to = ticket.getTo();
		Departure departure = departureRepository.findOne(ticket.getDeparture().getId());
		Long trainCapacity = departure.getTrain().getCapacity();
		int way = (from.getId() < to.getId()) ? FORWARD : BACKWARDS;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 1);
		Date after = calendar.getTime();
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
		Date before = calendar.getTime();
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_after = dateFormatter.format(after);
		String date_before = dateFormatter.format(before);
		
		List<Object[]> capacityList = (way == FORWARD) 
				? ticketRepository.findTicketsByStationForward(date_after, date_before, departure.getId().toString(), from.getId().toString(), to.getId().toString())
					:  ticketRepository.findTicketsByStationBackwards(date_after, date_before, departure.getId().toString(), from.getId().toString(), to.getId().toString());
				
		for (Object[] something : capacityList){
			BigInteger num_tickets = (BigInteger) something[1]; 
			if (num_tickets.longValue() >= trainCapacity) return true;
		}
		return false;	
	}
}
