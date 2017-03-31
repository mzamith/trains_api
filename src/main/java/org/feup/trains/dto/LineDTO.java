/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.dto;

import org.feup.trains.model.Line;

/**
 *
 * @author Renato Ayres
 */
public class LineDTO {

    private Long id;

    private String label;

    private String code;

    public LineDTO() {

    }

    private LineDTO(final Long id, final String label, final String code) {
	this.id = id;
	this.label = label;
	this.code = code;
    }

    public LineDTO(final Line line) {
	this(line.getId(), line.getLabel(), line.getCode());
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

}
