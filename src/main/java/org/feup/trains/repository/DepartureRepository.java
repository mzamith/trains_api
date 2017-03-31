package org.feup.trains.repository;

import java.util.List;
import org.feup.trains.model.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The DepartureRepository interface is a Spring Data JPA data repository for
 * Departure entities. The DepartureRepository provides all the data access
 * behaviors exposed by <code>JpaRepository</code> and additional custom
 * behaviors may be defined in this interface.
 *
 * @author Renato Ayres
 */
@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {

    /**
     * Query for a Departures entity by Destination. This method illustrates the
     * Query Method approach for query definition.
     *
     * <pre>
     * SELECT a FROM Departure a WHERE a.from = ?1
     * </pre>
     *
     * @param from A Long id of the Origin Station .
     * @return Collection of Departures or <code>null</code> if none found.
     */
    @Query("SELECT d FROM Departure d WHERE d.from.id = :from ORDER BY d.time ASC")
    List<Departure> findByOrigin(@Param("from") Long from);

    /**
     * Query for a Departures entity by Line. This method illustrates the Query
     * Method approach for query definition.
     *
     * <pre>
     * SELECT a FROM Departure a WHERE a.line = ?
     * </pre>
     *
     * @param line A Long id of the Origin Station .
     * @return Collection of Departures or <code>null</code> if none found.
     */
    @Query("SELECT d FROM Departure d WHERE d.line.id = :line")
    List<Departure> findAllByLine(@Param("line") Long line);

}
