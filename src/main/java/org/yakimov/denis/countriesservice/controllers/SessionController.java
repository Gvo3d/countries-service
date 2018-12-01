package org.yakimov.denis.countriesservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {

    @CrossOrigin
    @GetMapping(value = "session")
    public String getSession(HttpServletRequest request){
        return request.getSession().getId();
    }
}
