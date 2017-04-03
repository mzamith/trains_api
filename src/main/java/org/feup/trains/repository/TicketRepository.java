/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.repository;

import java.util.List;
import org.feup.trains.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Query for a Ticket list entity by Departure. This method illustrates the
     * Query Method approach for query definition.
     *
     * <pre>
     * SELECT a FROM Ticket t WHERE a.from = ?
     * </pre>
     *
     * @param departure A Long id of the Departure.
     * @return Collection of Ticket or <code>null</code> if none found.
     */
    @Query("SELECT t FROM Ticket t WHERE t.departure.id = :departure")
    List<Ticket> findAllByDeparture(@Param("departure") Long departure);
    
    /**
     * Query for a Ticket list entity by Account. This method illustrates the
     * Query Method approach for query definition.
     *
     * <pre>
     * SELECT a FROM Ticket t WHERE a.from = ?
     * </pre>
     *
     * @param departure A String of the Account username.
     * @return Collection of Ticket or <code>null</code> if none found.
     */
    @Query("SELECT t FROM Ticket t WHERE t.account.username = :username")
    List<Ticket> findAllByUsername(@Param("username") String username);

}
