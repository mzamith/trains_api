/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Trip class is an entity model object. It holds duration (expressed in
 * seconds), distance (expressed in meters) and the designated {@link Line}
 * between two {@link Station}.
 *
 * @author Renato Ayres
 */
@Entity
@Table(name = "trip")
public class Trip extends ReferenceEntity {

    /**
     * The Line in which this trip is part of.
     */
    @ManyToOne
    private Line line;

    /**
     * Station where the train comes from.
     */
    @ManyToOne
    private Station from;

    /**
     * Station where the train goes to.
     */
    @ManyToOne
    private Station to;

    /**
     * Duration of the trip, expressed in seconds.
     */
    private Long duration;

    /**
     * Distance of the trip, expressed in meters.
     */
    private Long distance;

    public Trip() {
	super();
    }

    public Line getLine() {
	return line;
    }

    public void setLine(Line line) {
	this.line = line;
    }

    public Station getFrom() {
	return from;
    }

    public void setFrom(Station from) {
	this.from = from;
    }

    public Station getTo() {
	return to;
    }

    public void setTo(Station to) {
	this.to = to;
    }

    public Long getDuration() {
	return duration;
    }

    /**
     * Returns the duration of the trip as a easy to manipulate object.
     *
     * @return duration as {@link LocalTime}
     */
    public LocalTime getDurationAsTime() {
	if (duration < 1) {
	    throw new IllegalStateException("Duration does not have a valid value."
		    + " It should be greater than zero.");
	}
	return LocalTime.ofSecondOfDay(this.duration);
    }

    public void setDuration(Long duration) {
	if (duration < 1) {
	    throw new IllegalArgumentException("Duration must be greater than 0,"
		    + " expressed in seconds.");
	}
	this.duration = duration;
    }

    public Long getDistance() {
	return distance;
    }

    public void setDistance(Long distance) {
	if (distance < 1) {
	    throw new IllegalArgumentException("Distance must be greater than 0,"
		    + " expressed in meters.");
	}
	this.distance = distance;
    }

}
