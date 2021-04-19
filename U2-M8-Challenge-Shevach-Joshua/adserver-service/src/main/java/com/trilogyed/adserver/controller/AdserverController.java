package com.trilogyed.adserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class AdserverController {

    private String[] ads = {
            "Home Equity Loans @ 3.87% APR",
            "Click HERE to qualify for a new car loan!",
            "Super Sale on summer clothes!",
            "Summer Show Blowout!!! 30% to 50% off!!!!",
            "Get a new phone NOW!",
            "Alaskan Cruises for as low as $700!",
            "BOGO large 2 topping pizzas!",
            "Free installation with the purchase of 100 square feet of carpet!"
    };

    private Random rnd = new Random();

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    String getAd() {
        return ads[rnd.nextInt(8)];
    }

}
