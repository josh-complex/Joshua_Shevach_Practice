package com.company.JoshuaShevachU1Capstone.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LowInventoryException extends RuntimeException {

    public LowInventoryException(String message) {
        super(message);
    }

}
