package org.fotm.java17.interfaces;

public record Octagon(String size, String color, String name) implements Polygon {
    // Not that name generates a name() method which makes need to implement name() happy

    @Override
    public int numberOfSides() {
        return 8;
    }
}
