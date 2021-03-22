package com.company.U1M4ChallengeShevachJoshua.controller;

import com.company.U1M4ChallengeShevachJoshua.model.Magic8Ball;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class Magic8BallController {

    private static int id = 0;

    private List<String> answers = Arrays.asList(
            "As I see it, yes.",
            "Ask again later.",
            "Better not tell you now.",
            "Cannot predict now.",
            "Concentrate and ask again.",
            "Don't count on it.",
            "It is certain.",
            "It is decidedly so."
    );

    @PostMapping("/magic8ball")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMagic8BallResponse(@RequestBody String question) {
        Magic8Ball response = new Magic8Ball(id++, question, answers.get(new Random().nextInt(9)));
        return response.toString();
    }

}
