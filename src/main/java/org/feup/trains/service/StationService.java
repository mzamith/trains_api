package org.feup.trains.service;

import java.util.Collection;

import org.feup.trains.model.Station;
import org.springframework.stereotype.Service;

public interface StationService {

	Collection<Station> findAll();

}
