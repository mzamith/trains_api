/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.feup.trains.model.Ticket;
import org.feup.trains.model.TicketState;

/**
 *
 * @author Renato Ayres
 */
public class TicketDTO {

    private Long id;

    private DepartureDTO departure;

    private StationDTO from;

    private StationDTO to;

    private Date day;

    private BigDecimal price;

    private TicketState state;

    private byte[] qrcode;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this(ticket.getId(),
                new DepartureDTO(ticket.getDeparture()),
                new StationDTO(ticket.getFrom()),
                new StationDTO(ticket.getTo()),
                ticket.getDay(),
                ticket.getPrice(),
                ticket.getState());
    }

    public TicketDTO(Long id, DepartureDTO departure, StationDTO from, StationDTO to, Date day, BigDecimal price, TicketState state) {
        this.id = id;
        this.departure = departure;
        this.from = from;
        this.to = to;
        this.day = day;
        this.price = price;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DepartureDTO getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureDTO departure) {
        this.departure = departure;
    }

    public StationDTO getFrom() {
        return from;
    }

    public void setFrom(StationDTO from) {
        this.from = from;
    }

    public StationDTO getTo() {
        return to;
    }

    public void setTo(StationDTO to) {
        this.to = to;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }

}
