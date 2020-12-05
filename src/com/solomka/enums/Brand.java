package com.solomka.enums;

import java.util.Random;

public enum Brand {
    TESLA,
    AUDI,
    BMW,
    TOYOTA,
    NISSAN;

    private static final Brand[] VALUES = values();
    private static final Random RANDOM = new Random();

    public static Brand randomBrand()  {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }
}
