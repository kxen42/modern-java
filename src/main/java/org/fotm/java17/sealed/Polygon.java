package org.fotm.java17.sealed;

public sealed interface Polygon extends Shape permits Hexagon, Octagon, Decagon {
    int numberOfSides();
}
