package org.fotm.java17.sealed;

import java.math.BigDecimal;

public class Ford implements Crappy {

    private BigDecimal price = BigDecimal.ZERO;
    private String crappy;
    private String cheap;

    public Ford(String cheap, String crappy, BigDecimal price) {
        this.cheap = cheap;
        this.crappy = crappy;
        this.price = price;
    }

    @Override
    public String howCrappy() {
        return crappy;
    }

    @Override
    public void setHowCrappy(String howCrappy) {
        this.crappy = howCrappy;
    }

    @Override
    public String howCheap() {
        return cheap;
    }

    @Override
    public void setHowCheap(String howCheap) {
        this.cheap = howCheap;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal price() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Ford{" +
            "cheap='" + cheap + '\'' +
            ", price=" + price +
            ", crappy='" + crappy + '\'' +
            '}';
    }
}
