/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.repository;

import org.feup.trains.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Renato Ayres
 */
public interface LineRepository extends JpaRepository<Line, Long> {

}
