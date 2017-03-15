/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The Train class is an entity model object. It represents a train, with a
 * passenger capacity.
 *
 * @author Renato Ayres
 */
@Entity
@Table(name = "train")
public class Train extends ReferenceEntity {

    /**
     * Passenger capacity of the train.
     */
    @NotNull
    private Long capacity;

    public Train() {
	super();
    }

    public Long getCapacity() {
	return capacity;
    }

    public void setCapacity(Long capacity) {
	if (capacity < 1) {
	    throw new IllegalArgumentException("Capacity of the train must be greater than 0.");
	}
	this.capacity = capacity;
    }
}
