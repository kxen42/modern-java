package org.fotm.model;

public final class Cat implements Animal {
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public void name(String name) {
        this.name = name;

    }

    public void meow(String message) {
        System.out.println("Meow " + message);

    }

    @Override
    public void move(String message) {
        System.out.println("Move " + message);
    }
}
