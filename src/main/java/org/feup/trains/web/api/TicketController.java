package org.feup.trains.web.api;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.feup.trains.exception.InvalidCardException;
import org.feup.trains.model.Station;
import org.feup.trains.model.Ticket;
import org.feup.trains.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TicketService ticketService;
    
    
    /**
     * Web service endpoint to fetch all Ticket entities. The service returns
     * the collection of Ticket entities as JSON.
     * 
     * @return A ResponseEntity containing a Collection of Ticket objects.
     */
    @RequestMapping(
            value = "/api/tickets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ticket>> getTickets() {
        logger.info("> getTickets");

        Collection<Ticket> tickets = ticketService.findAll();

        logger.info("< getTickets");
        return new ResponseEntity<Collection<Ticket>>(tickets,
                HttpStatus.OK);
    }

    
	@RequestMapping(value = "/api/ticket", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket> buyTicket(@RequestBody Ticket ticket, HttpServletRequest request) {
		logger.info("> buyTicket");

		try {
			Ticket savedTicket = ticketService.buyTicket(ticket, request);
			logger.info("< buyTicket");

			return new ResponseEntity<Ticket>(savedTicket, HttpStatus.CREATED);

		}catch (InvalidCardException ice){
			logger.error("Card is not Valid");
			//error 412
			return new ResponseEntity<Ticket>(HttpStatus.PRECONDITION_FAILED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Ticket>(HttpStatus.BAD_REQUEST);
		}

	}
}
