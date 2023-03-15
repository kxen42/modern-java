package org.fotm.java17.interfaces;

public interface Switch {

    default void turnOn() {
        System.out.println("default Switch.turnOn");
    }

    default void turnOff() {
        System.out.println("default Switch.turnOff");
    }
}
