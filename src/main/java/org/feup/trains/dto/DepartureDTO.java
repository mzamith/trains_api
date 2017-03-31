/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.dto;

import java.util.Date;
import org.feup.trains.model.Departure;

/**
 *
 * @author Renato Ayres
 */
public class DepartureDTO {

    private Long id;

    private String label;

    private String code;

    private LineDTO line;

    private StationDTO from;

    private TrainDTO train;

    private Date time;

    public DepartureDTO() {

    }

    private DepartureDTO(final Long id, final String label, final String code, final LineDTO line,
	    final StationDTO from, final TrainDTO train, final Date time) {
	super();
	this.id = id;
	this.label = label;
	this.code = code;
	this.line = line;
	this.from = from;
	this.train = train;
	this.time = time;
    }

    public DepartureDTO(final Departure departure) {
	this(departure.getId(),
		departure.getLabel(),
		departure.getCode(),
		new LineDTO(departure.getLine()),
		new StationDTO(departure.getFrom()),
		new TrainDTO(departure.getTrain()),
		departure.getTime());
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public LineDTO getLine() {
	return line;
    }

    public void setLine(LineDTO line) {
	this.line = line;
    }

    public StationDTO getFrom() {
	return from;
    }

    public void setFrom(StationDTO from) {
	this.from = from;
    }

    public TrainDTO getTrain() {
	return train;
    }

    public void setTrain(TrainDTO train) {
	this.train = train;
    }

    public Date getTime() {
	return time;
    }

    public void setTime(Date time) {
	this.time = time;
    }

}
