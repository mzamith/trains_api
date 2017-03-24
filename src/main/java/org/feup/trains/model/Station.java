/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Station class is an entity model object. It represents a train stop.
 *
 * @author Renato Ayres
 */
@Entity
@Table(name = "station")
public class Station extends ReferenceEntity {

    public Station() {
    	super();
    }

}
