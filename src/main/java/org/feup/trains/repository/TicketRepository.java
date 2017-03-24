/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.repository;

import org.feup.trains.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The TicketRepository interface is a Spring Data JPA data repository for
 * Ticket entities. The TicketRepository provides all the data access behaviors
 * exposed by <code>JpaRepository</code> and additional custom behaviors may be
 * defined in this interface.
 *
 * @author Renato Ayres
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}