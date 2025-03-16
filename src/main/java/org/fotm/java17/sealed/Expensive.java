package org.fotm.java17.sealed;

import java.math.BigDecimal;

public final class Expensive implements Product {
    private BigDecimal price;

    @Override
    public BigDecimal price() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;

    }
}
