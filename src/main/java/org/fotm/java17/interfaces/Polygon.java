package org.fotm.java17.interfaces;

public sealed interface Polygon extends Shape permits Hexagon, Octagon{
    int numberOfSides();
}
