package org.fotm.java17.sealed;

import java.math.BigDecimal;

public class Maverick extends Ford{
    public Maverick(String cheap, String crappy, BigDecimal price) {
        super(cheap, crappy, price);
    }

    @Override
    public String toString() {
        return "Maverick{} " + super.toString();
    }
}
