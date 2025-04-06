package org.fotm.java17.sealed;

public  record Decagon(String size, String name) implements Polygon {
    @Override
    public int numberOfSides() {
        return 10;
    }

    public String name() {
        return "The artist formerly known as  " + this.name;
    }
}
