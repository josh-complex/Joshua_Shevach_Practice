package com.company.U1M4ChallengeShevachJoshua.controller;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsIn.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {

    @Autowired
    MockMvc mockMvc;

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

    @Test
    public void shouldGetQuote() throws Exception {
        var result = mockMvc.perform(
                get("/quote"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", in(quotes.keySet())))
                .andExpect(jsonPath("$.quote", in(quotes.values())))
                .andReturn();
    }
}