package org.feup.trains.web.api;

import java.util.Collection;
import java.util.List;

import org.feup.trains.dto.TravelDTO;
import org.feup.trains.model.Station;
import org.feup.trains.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The TripService business service.
     */
    @Autowired
    private TripService tripService;
    
    
    /**
     * Web service endpoint to fetch all Station entities. The service returns
     * the collection of Station entities as JSON.
     * 
     * @return A ResponseEntity containing a Collection of Station objects.
     */
    @RequestMapping(
            value = "/api/travels",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TravelDTO>> getTravels(
    		@RequestParam(value="origin") Long originStationId,
    		@RequestParam(value="destination") Long destinationStationId) {
        
    	logger.info("> getTravels");

        List<TravelDTO> travels = tripService.findTravelBetweenStations(originStationId, destinationStationId);
        
        if (travels == null) {
            return new ResponseEntity<List<TravelDTO>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< getTravels");
        return new ResponseEntity<List<TravelDTO>>(travels,
                HttpStatus.OK);
    }

}









