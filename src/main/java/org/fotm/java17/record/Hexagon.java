package org.fotm.java17.record;
import org.fotm.java17.interfaces.Polygon;

public record Hexagon(String size, String name) implements Polygon{

    public int numberOfSides() {
        return 6;
    }
}
