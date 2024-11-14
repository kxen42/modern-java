package org.fotm.java8.optional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalExamples {
    public static void main(String[] args) {
        System.out.println(calculateAverageWithoutStream());
        System.out.println(calculateAverageWithoutStream(1, 2, 3));
        System.out.println(calculateAverageWithoutStream(0));
        System.out.println(calculateAverageWithoutStream(-1, 0, 1));
        handlePossibleNullValue(null);
        handlePossibleNullValue("");
        handlePossibleNullValue("boo");
        maxOfIntegers();

    }

    public static Optional<Double> calculateAverageWithoutStream(int... input) {
        System.out.println(" ----- calculateAverageWithoutStream ");
        if (input.length == 0) return Optional.empty();
        int sum = 0;
        for (int i : input) sum += i;
        return Optional.of((double) sum / input.length);
    }

    public static Optional<String> handlePossibleNullValue(String str) {
        System.out.println(" ----- handlePossibleNullValue [" + str + "]");
        // str == null ? Optional.empty() : Optional.of(str)
        Optional<String> str1 = Optional.ofNullable(str);
        System.out.println(str1);
        return str1;
    }

    public static void maxOfIntegers() {
        System.out.println(" ----- maxOfIntegers");
        List<Integer> li = Arrays.asList(22, 13, 34);
        Optional<Integer> oi = li.stream()
                                 .max(Comparator.comparing(Function.identity()));
        System.out.println(oi);
    }
}
