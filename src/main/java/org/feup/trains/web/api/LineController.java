/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.web.api;

import java.util.Collection;
import java.util.stream.Collectors;
import org.feup.trains.dto.DepartureDTO;
import org.feup.trains.dto.LineDTO;
import org.feup.trains.service.DepartureService;
import org.feup.trains.service.LineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Renato Ayres
 */
@RestController
public class LineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The LineService business service.
     */
    @Autowired
    private LineService lineService;

    /**
     * The DepartureService business service.
     */
    @Autowired
    private DepartureService departureService;

    /**
     * Web service endpoint to fetch all Line entities. The service returns the
     * collection of Line entities as JSON.
     *
     * @return A ResponseEntity containing a Collection of Line objects.
     */
    @RequestMapping(
            value = "/api/lines",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<LineDTO>> getLines() {
        logger.info("> getLines");

        Collection<LineDTO> lines = lineService
                .findAll()
                .stream()
                .map(l -> new LineDTO(l))
                .collect(Collectors.toList());

        return new ResponseEntity<>(lines, HttpStatus.OK);
    }

    /**
     * Web service endpoint to fetch all Departure entities given a Line id. The
     * service returns the collection of Departure entities as JSON.
     *
     * @param line
     * @return A ResponseEntity containing a Collection of Departure objects.
     */
    @RequestMapping(
            value = "/api/lines/{line}/departures",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DepartureDTO>> getLineDepartures(@PathVariable Long line) {
        logger.info("> getLineDepartures");

        Collection<DepartureDTO> departures = departureService.findAllByLine(line);

        return new ResponseEntity<>(departures, HttpStatus.OK);
    }

}
