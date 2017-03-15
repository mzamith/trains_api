/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Ticket class is an entity model object. It represents a ticket, with a
 * passenger capacity.
 *
 * @author Renato Ayres
 */
@Entity
@Table(name = "ticket")
public class Ticket extends ReferenceEntity {

    /**
     * The departure this ticket is supposed to be used at.
     */
    @ManyToOne
    private Departure departure;

    /**
     * The station this ticket is allowed to go.
     */
    @ManyToOne
    private Station to;

    /**
     * Price to pay for ticket.
     */
    private BigDecimal price;

    public Ticket() {
	super();
    }

    public Departure getDeparture() {
	return departure;
    }

    public void setDeparture(Departure departure) {
	this.departure = departure;
    }

    public Station getTo() {
	return to;
    }

    public void setTo(Station to) {
	this.to = to;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

}
