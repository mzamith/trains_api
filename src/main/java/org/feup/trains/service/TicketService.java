/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.feup.trains.exception.InvalidCardException;
import org.feup.trains.exception.TrainCapacityExceededException;
import org.feup.trains.model.Ticket;

/**
 *
 * @author Renato Ayres
 */
public interface TicketService {

    public Collection<Ticket> findAll();

    public Collection<Ticket> findAllByDeparture(Long departure);

    Collection<Ticket> findByAccount(HttpServletRequest request);

    Ticket buyTicket(Ticket ticket, HttpServletRequest request) throws InvalidCardException, TrainCapacityExceededException;

}
