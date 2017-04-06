package org.feup.trains.web.api;

import java.util.List;

import org.feup.trains.dto.TimetableDTO;
import org.feup.trains.dto.TravelDTO;
import org.feup.trains.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {
	
	private static final int FORWARD = 1;
	private static final int BACKWARDS = 2;
	
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
    
    /**
     * Web service endpoint to fetch Timeline entities. The service returns
     * the collection of Timeline entities as JSON.
     * 
     * @return A ResponseEntity containing a Timeline objects.
     */
    @RequestMapping(
            value = "/api/timetable/{way}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimetableDTO> getTimeline(
    		@PathVariable("way") int way) {
    	
    	logger.info("> getTimetable");
    	
    	TimetableDTO timeline;
    	
    	switch (way) {
    		case FORWARD: 
    			timeline = tripService.findForwardTimeline();
    			break;
    		case BACKWARDS: 
    			timeline = tripService.findBackwardsTimeline();
    			break;
    		default: 
    			timeline = null;
    			break;
    	}
    	
    	logger.info("< getTimetable");
    	
    	if (timeline == null){
    		return new ResponseEntity<TimetableDTO>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    	return new ResponseEntity<TimetableDTO>(timeline,
    				HttpStatus.OK);
    	
    }

}









