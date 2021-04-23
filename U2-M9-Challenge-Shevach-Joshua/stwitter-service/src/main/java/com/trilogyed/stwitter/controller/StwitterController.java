package com.trilogyed.stwitter.controller;

import com.trilogyed.stwitter.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StwitterController {


    ServiceLayer service;

    public StwitterController(ServiceLayer service) {
        this.service = service;
    }
}
