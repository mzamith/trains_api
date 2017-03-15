/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Departure class is an entity model object. It holds information about a
 * daily train departure such as time of day, {@link Station} of departure,
 * designated {@link Line} and {@link Train}.
 *
 * @author Renato Ayres
 */
@Entity
@Table(name = "departure")
public class Departure extends ReferenceEntity {

    /**
     * Station of departure.
     */
    @ManyToOne
    private Station from;

    /**
     * Daily time of departure.
     */
    private Date time;

    /**
     * Line the train will comply to.
     */
    @ManyToOne
    private Line line;

    @ManyToOne
    private Train train;

    public Departure() {
	super();
    }

    public Station getFrom() {
	return from;
    }

    public void setFrom(Station from) {
	this.from = from;
    }

    public Date getTime() {
	return time;
    }

    public void setTime(Date time) {
	this.time = time;
    }

    public Line getLine() {
	return line;
    }

    public void setLine(Line line) {
	this.line = line;
    }

    public Train getTrain() {
	return train;
    }

    public void setTrain(Train train) {
	this.train = train;
    }

}
