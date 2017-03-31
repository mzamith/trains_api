/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import org.feup.trains.model.Ticket;

/**
 *
 * @author Renato Ayres
 */
public interface TicketService {

    public Collection<Ticket> findAll();

    public Collection<Ticket> findAllByDeparture(Long departure);

}
