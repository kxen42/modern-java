package org.fotm.java17.interfaces;

public interface NewStyleInterface {

    static String staticMethod() {
        return "interface static method";
    }

    default String defaultMethod() { return "NewStyleInterface default";}

    String oldFashionedMethod();
}
