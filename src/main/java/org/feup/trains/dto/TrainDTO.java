/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.dto;

import org.feup.trains.model.Train;

/**
 *
 * @author Renato Ayres
 */
public class TrainDTO {

    private Long id;

    private String label;

    private String code;

    private Long capacity;

    public TrainDTO() {

    }

    private TrainDTO(final Long id, final String label, final String code, final Long capacity) {
	this.id = id;
	this.label = label;
	this.code = code;
	this.capacity = capacity;
    }

    public TrainDTO(final Train train) {
	this(train.getId(), train.getLabel(), train.getCode(), train.getCapacity());
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

    public Long getCapacity() {
	return capacity;
    }

    public void setCapacity(Long capacity) {
	this.capacity = capacity;
    }

}
