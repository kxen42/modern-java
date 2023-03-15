package org.fotm.java17.interfaces;

public class InterfaceWithMainMethod {

    static String staticMethod() {
        return "boo";
    }

    public static void main(String[] args) {
        System.out.println(InterfaceWithMainMethod.staticMethod());
    }
}
