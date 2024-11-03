package org.fotm.java17.interfaces;

public class Phone implements Device, Switch {

    @Override
    public void turnOn() {
        Device.super.turnOn();
    }

    @Override
    public void turnOff() {
        Switch.super.turnOff();
    }
}
