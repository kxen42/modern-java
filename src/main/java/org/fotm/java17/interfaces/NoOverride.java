package org.fotm.java17.interfaces;

public class NoOverride implements NewStyleInterface {
    @Override
    public String oldFashionedMethod() {
        return "NoOverride old fashioned method impl";
    }
}
