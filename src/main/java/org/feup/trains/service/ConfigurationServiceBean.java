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

}
