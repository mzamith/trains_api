package org.feup.trains.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/users") /* Maps to all HTTP actions by default (GET,POST,..)*/
    public @ResponseBody
    String getUsers() {
        return "hello";
    }

}
