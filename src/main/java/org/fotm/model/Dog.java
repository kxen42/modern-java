package org.fotm.model;

public final class Dog implements Animal {

    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public void name(String name) {
        this.name = name;

    }

    public void bark(String message) {
        System.out.println("Bark!!! " + message);
    }

    @Override
    public void move(String message) {
        System.out.println("Move " + message);
    }
}
