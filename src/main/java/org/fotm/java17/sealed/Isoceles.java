package org.fotm.java17.sealed;

import org.fotm.java17.interfaces.Triangle;

public class Isoceles implements Triangle {
    @Override
    public String name() {
        return "Isoceles";
    }

    @Override
    public void spewName() {
        System.out.println("From non-sealed interface");
        Triangle.super.spewName();
    }

    public static void main(String[] args) {
        var t = new Isoceles();
        t.meToo();
        t.spewName();
    }
}
