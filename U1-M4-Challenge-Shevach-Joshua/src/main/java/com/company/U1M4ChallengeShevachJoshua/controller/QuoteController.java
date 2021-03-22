package com.company.U1M4ChallengeShevachJoshua.controller;

import com.company.U1M4ChallengeShevachJoshua.model.Quote;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class QuoteController {

    private static int id = 0;

    Map<String, String> quotes = new HashMap<String, String>() {{
        put("Friedrich Nietzsche", "That which does not kill us makes us stronger.");
        put("Virginia Satir", "We must not allow other people’s limited perceptions to define us.");
        put("Theodore Roosevelt", "Do what you can, with what you have, where you are.");
        put("Oscar Wilde", "Be yourself; everyone else is already taken.");
        put("William Shakespeare", "This above all: to thine own self be true.");
        put("Napoleon Hill", "If you cannot do great things, do small things in a great way.");
        put("Albert Einstein", "Strive not to be a success, but rather to be of value.");
        put("Plato", "Wise men speak because they have something to say; fools because they have to say something.");
        put("John Wooden", "Do not let what you cannot do interfere with what you can do.");
        put("Mark Twain", "Whenever you find yourself on the side of the majority, it is time to pause and reflect.");
    }};

    @GetMapping("/quote")
    @ResponseStatus(HttpStatus.OK)
    public Quote getQuote() {
        int random = new Random().nextInt(10);

        var keys = quotes.keySet().toArray();
        var values = quotes.values().toArray();

        return new Quote(id++, keys[random].toString(), values[random].toString());
    }

}
