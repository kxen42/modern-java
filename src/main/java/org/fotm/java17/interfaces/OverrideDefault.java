package org.fotm.java17.interfaces;

public class OverrideDefault implements NewStyleInterface {
    @Override
    public String defaultMethod() {
        return "OverrideDefault overriding NewStyleInterface default\"";
    }

    @Override
    public String oldFashionedMethod() {
        return "OverrideDefault old fashioned interface method impl";
    }
}
