/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.repository;

import java.util.Date;
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
    
    @Query("SELECT t FROM Ticket t WHERE t.day >= :after AND t.day < :before")
    List<Ticket> findBetweenDates(@Param("after") Date after, @Param("before") Date before);
    
    @Query(value = "SELECT s.id, (SELECT count(*) FROM TICKET t where (t.day between ?1 AND ?2) AND (s.id between t.from_station and t.to_station) AND t.departure = ?3) as num_tickets from STATION s WHERE s.id >= ?4 AND s.id <= ?5 group by s.id", nativeQuery = true)
    List<Object[]> findTicketsByStationForward(@Param("after") String after, @Param("before") String before, @Param("departure") String departureID, @Param("from") String fromId, @Param("to") String toId);
    
    @Query(value = "SELECT s.id, (SELECT count(*) FROM TICKET t where (t.day between ?1 AND ?2) AND (s.id between t.to_station and t.from_station) AND t.departure = ?3) as num_tickets from STATION s WHERE s.id >= ?5 AND s.id <= ?4 group by s.id", nativeQuery = true)
    List<Object[]> findTicketsByStationBackwards(@Param("after") String after, @Param("before") String before, @Param("departure") String departureID, @Param("from") String fromId, @Param("to") String toId);
    
}
