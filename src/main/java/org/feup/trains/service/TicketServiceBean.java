/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;

import org.feup.trains.exception.InvalidCardException;
import org.feup.trains.model.Account;
import org.feup.trains.model.Ticket;
import org.feup.trains.model.TicketState;
import org.feup.trains.repository.AccountRepository;
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

	/**
	 * The Spring Data repository for Ticket entities.
	 */
	@Autowired
	private TicketRepository ticketRepository;

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
	public Ticket buyTicket(Ticket ticket, HttpServletRequest request) throws InvalidCardException {

		// Ensure the entity object to be created does NOT exist in the
		// repository. Prevent the default behavior of save() which will update
		// an existing entity if the entity matching the supplied id exists.
		if (ticket.getId() != null) {
			// Cannot create Ticket with specified ID value
			logger.error("Attempted to create a Exam, but id attribute was not null.");
			throw new EntityExistsException("The id attribute must be null to persist a new entity.");
		}

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

}
