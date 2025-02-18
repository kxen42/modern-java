package org.fotm.java17.interfaces;

public sealed interface Shape  permits Circle, Square{
    String name();

    default void spewName() {
        System.out.println("Shape name is " + name());
    }
}
