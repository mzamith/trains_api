/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
    @ManyToOne(
	    fetch = FetchType.EAGER,
	    optional = false)
    @JoinColumn(name = "departure")
    private Departure departure;

    /**
     * The station this ticket is allowed to go.
     */
    @ManyToOne(
	    fetch = FetchType.EAGER,
	    optional = false)
    @JoinColumn(name = "to_station")
    private Station to;
    
    /**
     * The account the ticket belongs to
     */
	@ManyToOne(
			fetch = FetchType.EAGER,
			optional = false)
	@JoinColumn(name = "account_id")
    private Account account;

    /**
     * Price to pay for ticket.
     */
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    private TicketState state;

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
    
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TicketState getState() {
		return state;
	}

	public void setState(TicketState state) {
		this.state = state;
	}

}
