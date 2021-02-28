package com.company.utilities;

import com.company.utilities.abstracts.Utility;

public class Light extends Utility {

    public Light() {
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        System.out.println("Light is on: " + active);
    }
}
