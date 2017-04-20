/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.feup.trains.dto.DepartureDTO;
import org.feup.trains.repository.DepartureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Renato Ayres
 */
@org.springframework.stereotype.Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class DepartureServiceBean implements DepartureService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The Spring Data repository for Departure entities.
     */
    @Autowired
    private DepartureRepository departureRepository;

    @Override
    public Collection<DepartureDTO> findAll() {
        logger.info("> findAll");

        return departureRepository.findAll()
                .stream()
                .map(d -> new DepartureDTO(d))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<DepartureDTO> findAllByLine(Long line) {
        logger.info("> findAllByLine");

        return departureRepository.findAllByLine(line)
                .stream()
                .map(d -> new DepartureDTO(d))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<DepartureDTO> findAll(List<Long> departures) {
        return departureRepository.findAll(departures)
                .stream()
                .map(d -> new DepartureDTO(d))
                .collect(Collectors.toList());
    }

}
