/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Line class is an entity model object. It represents a path of train
 * stops.
 *
 * @author Renato Ayres
 */
@Entity
@Table(name = "line")
public class Line extends ReferenceEntity {

    public Line() {
    	super();
    }

}
