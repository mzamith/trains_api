package org.feup.trains.service;

import java.util.Collection;

import org.feup.trains.model.Station;
import org.feup.trains.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class StationServiceBean implements StationService {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The Spring Data repository for Greeting entities.
     */
    @Autowired
    private StationRepository stationRepository;

	@Override
	public Collection<Station> findAll() {
		
        logger.info("> findAllStations");

        Collection<Station> stations = stationRepository.findAll();

        logger.info("< findAllStations");
        return stations;
	}

}
