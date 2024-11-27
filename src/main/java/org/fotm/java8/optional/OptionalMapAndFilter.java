package org.fotm.java8.optional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Examples of {@code map}, {@code flatMap}, {@code filter}, {@code or}.
 * <p>
 * {@code Optional} has a few methods that do the same thing that {@code Stream} methods with the same names do, but
 * instead of {@code Stream}s they return {@code Optional}s.
 * <p>
 * Samples from <em>dilipsundarraj1</em> are in the linked GitHub repo. I made some alterations to these to expand on something
 * I was curious about.
    *
    * @see <a href="https://github.com/dilipsundarraj1/java-8/tree/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/optional">dilipsundarraj1/java-8</a>
    */
public class OptionalMapAndFilter {

    public static void main(String[] args) {
        or(false);
        or(true);
    }

    /**
     * Demo {@code Optional.or()}.
     * <p>
     * The difference between {@code or(Supplied T)} and {@code orElse(T)}, is that  {@code or} takes a {@code Supplier<T>}.
     * It was introduced in Java 9 so some must've found a need for it. Difference between or and the orElse's is that {@code or}
     * returns a value wrapped in {@code Optional} where the others return a plain value.
     * @param isFound flag to simulate value is found
     */
    public static void or(boolean isFound) {

        Optional<String> notEmpty = Optional.of("Barney");
        Optional<String> isEmpty = Optional.empty();
        Supplier<Optional<String>> defaultSupplier = () -> Optional.of("Fred");

        if (isFound) {
            Optional<String> result = notEmpty.or(defaultSupplier);
            System.out.printf("isFound: %s, result %s%n", isFound, result);
        } else {
            Optional<String> result = isEmpty.or(defaultSupplier);
            System.out.printf("isFound: %s, result %s%n", isFound, result);
        }

    }
}
