package org.feup.trains.service;

import org.feup.trains.model.Configuration;
import org.feup.trains.repository.ConfigurationRepository;
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
public class ConfigurationServiceBean implements ConfigurationService {
	
	private static final String PRICE = "trains-cents-per-kilometre";
	private static final String FIRST_STATION = "trains-first-station-id";
	private static final String LAST_STATION = "trains-last-station-id";
	private static final String EXPIRATION = "tickets-expiration-duration-days";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The Spring Data repository for Greeting entities.
     */
    @Autowired
    private ConfigurationRepository configurationRepository;
	
	@Override
	public int getPriceConfiguration() {
		
		Configuration conf = configurationRepository.findByKey(PRICE);
		return Integer.parseInt(conf.getValue());
	}
	
	@Override
	public long getFirstStation() {
		
		Configuration conf = configurationRepository.findByKey(FIRST_STATION);
		return Long.parseLong(conf.getValue());
	}
	
	@Override
	public long getLastStation() {
		
		Configuration conf = configurationRepository.findByKey(LAST_STATION);
		return Long.parseLong(conf.getValue());
	}
	
	@Override
	public int getExpirationDays() {
		
		Configuration conf = configurationRepository.findByKey(EXPIRATION);
		return Integer.parseInt(conf.getValue());
	}

}
