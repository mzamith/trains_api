package org.feup.trains.web.api;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.feup.trains.dto.TicketDTO;
import org.feup.trains.dto.TicketInspectorDTO;
import org.feup.trains.exception.InvalidCardException;
import org.feup.trains.exception.TrainCapacityExceededException;
import org.feup.trains.model.Ticket;
import org.feup.trains.service.TicketService;
import org.feup.trains.util.Encrypter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Collection<TicketDTO>> getTickets() {
        logger.info("> getTickets");

        Collection<TicketDTO> tickets = ticketService
                .findAll()
                .stream()
                .map(m -> new TicketDTO(m))
                .collect(Collectors.toList());

        logger.info("< getTickets");
        return new ResponseEntity<>(tickets,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/api/ticket",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDTO> buyTicket(@RequestBody Ticket ticket, HttpServletRequest request) {
        logger.info("> buyTicket");

        try {
            Ticket temp = ticketService.buyTicket(ticket, request);

            Gson gson = new Gson();

            TicketDTO savedTicket = new TicketDTO(temp);

            byte[] code = Encrypter.encrypt(gson.toJson(new TicketInspectorDTO(temp)));
            savedTicket.setQrcode(code);
            logger.info("< buyTicket");

            return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);

        } catch (InvalidCardException ice) {
            logger.error("Card is not Valid");
            //error 412
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        } catch (TrainCapacityExceededException tcee) {
            logger.error("Train is full");
            //error 406
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Web service endpoint to fetch all Ticket entities for a given user. The
     * service returns the collection of Ticket entities as JSON.
     *
     * @return A ResponseEntity containing a Collection of Ticket objects.
     */
    @RequestMapping(
            value = "/api/usertickets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Ticket>> getUserTickets(HttpServletRequest request) {
        logger.info("> getTickets");

        Collection<Ticket> tickets = ticketService.findByAccount(request);

        logger.info("< getTickets");
        return new ResponseEntity<Collection<Ticket>>(tickets,
                HttpStatus.OK);
    }
}
