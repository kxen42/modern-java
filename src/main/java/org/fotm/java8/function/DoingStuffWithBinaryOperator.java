package org.fotm.java8.function;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class DoingStuffWithBinaryOperator {

    private static final BinaryOperator<Double> tangent = (x, y) -> Math.sin(x) / Math.cos(y);

    private static final Comparator<String> compareByLength = Comparator.comparingInt(String::length);

    private static final BinaryOperator<String> concatenated = String::concat;

    public static void main(String[] args) {

        System.out.println("tangent x & y: " + tangent.apply(Math.PI, 2.0));

        BinaryOperator<String> maxByOperator = BinaryOperator.maxBy(compareByLength);
        String maxBy = maxByOperator.apply("boo", "ouch");

        System.out.println(maxBy);

        BinaryOperator<String> minByOperator = BinaryOperator.minBy(compareByLength);
        String minBy = minByOperator.apply("boo", "ouch");

        System.out.println(minBy);

        String mergedAndThenToUpperCase = concatenated.andThen(String::toUpperCase)
                                                      .apply("boo", "ouch");

        System.out.println(mergedAndThenToUpperCase);
    }
}
