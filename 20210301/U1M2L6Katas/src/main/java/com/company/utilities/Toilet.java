package com.company.utilities;

import com.company.utilities.abstracts.Utility;

public class Toilet extends Utility {

    public Toilet() {
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        if(!isActive()) flush();
        System.out.println("Toilet is in use: " + active);
    }

    private void flush() {
        System.out.println("I flushed the toilet!");
    }
}
