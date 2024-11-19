package org.fotm.java8.function;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class DoingStuffWithBinaryOperator {

    private static final BinaryOperator<Double> tangent = (x, y) -> Math.sin(x) / Math.cos(y);

    private static final Comparator<String> compareByLength = Comparator.comparingInt(String::length);

    private static final BinaryOperator<String> concatenated = String::concat;
    private static final BinaryOperator<Double> multiplyMethodReference = DoingStuffWithBinaryOperator::multiply;

    // to demonstrate usage as a method reference
    private static Double multiply(Double x, Double y) {
        return x * y;
    }

    public static void main(String[] args) {

        System.out.println(" ----- BinaryOperator.apply");
        System.out.println("tangent x & y: " + tangent.apply(Math.PI, 2.0));

        System.out.println(" ----- BinaryOperator.maxBy");
        BinaryOperator<String> maxByOperator = BinaryOperator.maxBy(compareByLength);
        String maxBy = maxByOperator.apply("boo", "ouch");

        System.out.println(maxBy);

        System.out.println(" ----- BinaryOperator.minBy");
        BinaryOperator<String> minByOperator = BinaryOperator.minBy(compareByLength);
        String minBy = minByOperator.apply("boo", "ouch");

        System.out.println(minBy);

        System.out.println(" ----- BinaryOperator.andThen");
        String mergedAndThenToUpperCase = concatenated.andThen(String::toUpperCase)
                                                      .apply("boo", "ouch");

        System.out.println(mergedAndThenToUpperCase);

        System.out.println(" ----- BinaryOperator implemented by Method Reference");
        Stream.of(new Double[]{3.0, 7.0}, new Double[]{6.0, 8.0})
              .map(a -> multiplyMethodReference.apply(a[0], a[1]))
              .forEach(System.out::println);

    }
}
