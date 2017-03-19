package org.feup.trains.web.api;

import java.util.Collection;

import org.feup.trains.model.Station;
import org.feup.trains.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {
	

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The GreetingService business service.
     */
    @Autowired
    private StationService stationService;
	
    /**
     * Web service endpoint to fetch all Station entities. The service returns
     * the collection of Station entities as JSON.
     * 
     * @return A ResponseEntity containing a Collection of Station objects.
     */
    @RequestMapping(
            value = "/api/stations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Station>> getStations() {
        logger.info("> getStations");

        Collection<Station> stations = stationService.findAll();

        logger.info("< getStations");
        return new ResponseEntity<Collection<Station>>(stations,
                HttpStatus.OK);
    }

}
