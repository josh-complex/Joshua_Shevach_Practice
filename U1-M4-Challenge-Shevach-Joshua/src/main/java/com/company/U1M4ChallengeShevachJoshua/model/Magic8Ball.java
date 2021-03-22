package com.company.U1M4ChallengeShevachJoshua.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Magic8Ball {

    private int id;
    private String question;
    private String answer;

    @Override
    public String toString() {
        return answer;
    }

}
