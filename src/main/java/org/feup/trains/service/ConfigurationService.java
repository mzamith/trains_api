package org.feup.trains.service;

public interface ConfigurationService {
	
	int getPriceConfiguration();
	
	long getFirstStation();
	
	long getLastStation();
	
	int getExpirationDays();

}
