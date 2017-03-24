/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.repository;

import java.util.Collection;
import java.util.List;

import org.feup.trains.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The TripRepository interface is a Spring Data JPA data repository for Trip
 * entities. The TripRepository provides all the data access behaviors exposed
 * by <code>JpaRepository</code> and additional custom behaviors may be defined
 * in this interface.
 *
 * @author Renato Ayres
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	
    /**
     * Find Forward trips knowing Destination (from) and Origin (to)
     * @param from Station ID of Origin
     * @param to Station ID of Destination
     * @return Trips
     */
    @Query("SELECT t FROM Trip t WHERE t.from.id < t.to.id AND t.from.id >= :from AND t.to.id <= :to ORDER BY t.from.id ASC")
    List<Trip> findForwardTrips(@Param("from") Long from, @Param("to") Long to);
    
    
    /**
     * Find Backwards trips knowing Destination (from) and Origin (to)
     * @param from Station ID of Origin
     * @param to Station ID of Destination
     * @return Trips
     */
    @Query("SELECT t FROM Trip t WHERE t.from.id > t.to.id AND t.from.id <= :from AND t.to.id >= :to ORDER BY t.from.id DESC")
    List<Trip> findBackwardsTrips(@Param("from") Long from, @Param("to") Long to);

}
