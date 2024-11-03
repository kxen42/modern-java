package org.fotm.java17.methodreference;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * by Sean Kennedy
 */
public class MainBoundMethodReference {
    public static void main(String[] args) {
        boundMethodReferences();
    }

    public static void boundMethodReferences() {
        String name = "Mr. Joe Bloggs"; // The type of name is know at compile-time

        Supplier<String> lowerL = () -> name.toLowerCase();   // lambda
        Supplier<String> lowerMR = name::toLowerCase;          // method reference

        // No need to say which instance to call it on - the supplier is bound to name
        System.out.println(lowerL.get()); // mr. joe bloggs
        System.out.println(lowerMR.get());// mr. joe bloggs

        // Using a bound method reference with functional thing that takes an argument
        // Even though startsWith is overloaded, startsWith(String) and
        // startsWith(String, int), because we are creating a Predicate which
        // has a functional method of test(T t), the startsWith(String) is used.

        // This is where "context" is important.
        Predicate<String> titleL = (title) -> name.startsWith(title);
        Predicate<String> titleMR = name::startsWith;

        System.out.println(titleL.test("Mr.")); // true
        System.out.println(titleMR.test("Ms."));// false

        // Demonstrate the importance of context
        String slogan = "YabbaDabbaDoo";
        BiFunction<String, Integer, Boolean> alternateStartsWith = slogan::startsWith;

        System.out.println(alternateStartsWith.apply("Dab", 5));
        System.out.println(alternateStartsWith.apply("Dab", 1));
    }
}
