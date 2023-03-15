package org.fotm.java17.interfaces;

public interface Device {

    default void turnOn() {
        System.out.println("default Device.turnOn");
    }

    default void turnOff() {
        System.out.println("default Device.turnOff");
    }
}
