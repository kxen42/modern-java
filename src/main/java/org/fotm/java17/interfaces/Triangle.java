package org.fotm.java17.interfaces;

public  non-sealed interface Triangle extends Shape {
    default void meToo() {
        System.out.println("I'm a triangle " + name());
    }
}
