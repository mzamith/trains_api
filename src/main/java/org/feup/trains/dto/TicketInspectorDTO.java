/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.dto;

import org.feup.trains.model.Ticket;

/**
 *
 * @author Renato Ayres
 */
public class TicketInspectorDTO {

    private Long ticket;

    private Long departure;

    private String departureLabel;

    private String username;

    public TicketInspectorDTO() {
    }

    public TicketInspectorDTO(Long ticket, Long departure, String departureLabel, String username) {
        this.ticket = ticket;
        this.departure = departure;
        this.departureLabel = departureLabel;
        this.username = username;
    }

    public TicketInspectorDTO(Ticket ticket) {
        this(ticket.getId(),
                ticket.getDeparture().getId(),
                ticket.getDeparture().getCode(),
                ticket.getAccount().getUsername());
    }

    public TicketInspectorDTO(TicketDTO ticket, String username) {
        this(ticket.getId(),
                ticket.getDeparture().getId(), ticket.getDeparture().getCode(),
                username);
    }

    public Long getTicket() {
        return ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    public Long getDeparture() {
        return departure;
    }

    public void setDeparture(Long departure) {
        this.departure = departure;
    }

    public String getDepartureLabel() {
        return departureLabel;
    }

    public void setDepartureLabel(String departureLabel) {
        this.departureLabel = departureLabel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
