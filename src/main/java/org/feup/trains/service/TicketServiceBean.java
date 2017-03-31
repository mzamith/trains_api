/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import org.feup.trains.model.Ticket;
import org.feup.trains.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Renato Ayres
 */
@org.springframework.stereotype.Service
@Transactional(
	propagation = Propagation.SUPPORTS,
	readOnly = true)
public class TicketServiceBean implements TicketService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The Spring Data repository for Ticket entities.
     */
    @Autowired
    private TicketRepository ticketRepository;

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

}
