package org.fotm.java8.optional;

import java.util.Optional;

public class OptionalExamples {
    public static void main(String[] args) {
        calculateAverageWithoutStream();
    }

    public static Optional<Double> calculateAverageWithoutStream(int... input) {
        System.out.println(" ----- calculateAverageWithoutStream");
        if (input.length == 0) return Optional.empty();
        int sum = 0;
        for (int i : input) sum += i;
        return Optional.of((double) sum / input.length);

    }

    public static Optional<String> handlePossibleNullValue(String str) {
        // str == null ? Optional.empty() : Optional.of(str)
        return Optional.ofNullable(str);
    }
}
