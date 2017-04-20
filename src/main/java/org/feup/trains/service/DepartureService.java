/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import java.util.List;
import org.feup.trains.dto.DepartureDTO;

/**
 *
 * @author Renato Ayres
 */
public interface DepartureService {

    public Collection<DepartureDTO> findAll();

    public Collection<DepartureDTO> findAllByLine(Long line);

    public Collection<DepartureDTO> findAll(List<Long> departure);

}
