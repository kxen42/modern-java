package org.fotm.java17.sealed;

public record Hexagon(String size, String name) implements Polygon {

    public int numberOfSides() {
        return 6;
    }
}
