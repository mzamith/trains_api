/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Renato Ayres
 */
@RestController
public class DepartureController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    @RequestMapping("/test")
    public @ResponseBody
    String getUsers() {
	return "Sup? We da departures cuz!";
    }

}
