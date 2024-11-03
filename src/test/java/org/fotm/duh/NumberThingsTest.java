package org.fotm.duh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberThingsTest {

    private final NumberThings numberThings = new NumberThings();

    @Test
    void parseFloat() {
        assertEquals(42.0f, numberThings.parseFloat("42"));
        assertEquals(Float.NaN, numberThings.parseFloat("boo"));
    }
}