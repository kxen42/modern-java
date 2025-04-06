package org.fotm.java17.sealed;

public sealed interface Shape permits Circle, Square, Triangle, Polygon {
    String name();

    default void spewName() {
        System.out.println("Shape name is " + name());
    }
}
