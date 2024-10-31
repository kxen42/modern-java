package org.fotm.duh;

/**
 * Features of Numbers that I never used.
 */
public class NumberThings {

    public Float parseFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return Float.NaN;
        }
    }
}
