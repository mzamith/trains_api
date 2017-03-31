/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.web.api;

import java.util.Collection;
import org.feup.trains.model.Ticket;
import org.feup.trains.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Renato Ayres
 */
@RestController
public class DepartureController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The TicketService business service.
     */
    @Autowired
    private TicketService ticketService;

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    @RequestMapping("/test")
    public @ResponseBody
    String getUsers() {
	return "Sup? We da departures cuz!";
    }

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    @RequestMapping(
	    value = "/inspector/departures/{departure}/tickets",
	    method = RequestMethod.GET,
	    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ticket>> getDepartureTickets(@PathVariable Long departure) {
	logger.info("> getLines");

	Collection<Ticket> tickets = ticketService.findAllByDeparture(departure);

	return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}
