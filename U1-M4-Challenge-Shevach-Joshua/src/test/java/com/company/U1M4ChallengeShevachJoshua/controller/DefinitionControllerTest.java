package com.company.U1M4ChallengeShevachJoshua.controller;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.collection.IsIn.in;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DefinitionController.class)
public class DefinitionControllerTest {

    @Autowired
    MockMvc mockMvc;

    Map<String, String> dictionary = new TreeMap<String, String>() {{
        put("abecedarian", "arranged in alphabetical order");
        put("foozle", "to bungle; play clumsily");
        put("vernal", "of or relating to spring");
        put("gasconade", "extravagant boasting; boastful talk");
        put("nescience", "lack of knowledge; ignorance");
        put("macushla", "darling");
        put("haimish", "homey; cozy and unpretentious");
        put("lickety-split", "at great speed; rapidly");
        put("flummoxed", "utterly bewildered, confused, or puzzled");
        put("foible", "a minor weakness or failing of character; slight flaw or defect");
    }};

    @Test
    public void shouldGetWordDefinition() throws Exception {
        var result = mockMvc.perform(
                get("/word"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.word", in(dictionary.keySet())))
                .andExpect(jsonPath("$.definition", in(dictionary.values())))
                .andReturn();
    }
}