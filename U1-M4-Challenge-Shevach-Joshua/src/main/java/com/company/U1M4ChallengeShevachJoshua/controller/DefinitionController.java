package com.company.U1M4ChallengeShevachJoshua.controller;

import com.company.U1M4ChallengeShevachJoshua.model.Definition;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DefinitionController {

    private static int id = 0;

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

    @GetMapping("/word")
    @ResponseStatus(HttpStatus.OK)
    public Definition getWordDefinition() {
        int random = new Random().nextInt(10);

        var keys = dictionary.keySet().toArray();
        var values = dictionary.values().toArray();

        return new Definition(id++, keys[random].toString(), values[random].toString());
    }

}
