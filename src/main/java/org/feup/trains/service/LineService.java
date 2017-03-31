/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.service;

import java.util.Collection;
import org.feup.trains.model.Line;

/**
 *
 * @author Renato Ayres
 */
public interface LineService {

    public Collection<Line> findAll();

}
