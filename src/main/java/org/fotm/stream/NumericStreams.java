package org.fotm.stream;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class NumericStreams {

    public static void main(String[] args) {
        intStream();
        aggregateNumericStreamMethods();
        unboxing();
    }

    /**
     * IntStream creates a stream of primitive ints.
     * To map to a string, mapToObj(String::valueOf)
     * map(Integer::toString) will not work.
     */
    public static void intStream() {
        System.out.println(" ----- intStream");

        long rangeCount = IntStream.range(1, 50)
                                   .count();
        long rangeClosedCount = IntStream.rangeClosed(1, 50)
                                         .count();
        System.out.println("rangeCount: " + rangeCount + ", rangeClosedCount: " + rangeClosedCount);

        String str = IntStream.range(-2, 4)
                              .mapToObj(String::valueOf)
                              .collect(joining(", "));
        System.out.println("start at negative to positive: " + str);

        // can't go from positive to negative
        long count = IntStream.range(1, -2)
                              .count();
        System.out.println("start at positive to negative for range size: " + count);

        // DoubleStream does not hava a range or rangeClosed, but you can use Int/LongStream to do it.
        String doubleRange = IntStream.range(1, 4)
                                      .asDoubleStream()
                                      .mapToObj(String::valueOf)
                                      .collect(joining(", "));
        System.out.println("doubleRange: " + doubleRange);

        doubleRange = IntStream.range(1, 4)
                               .asDoubleStream()
                               .map(x -> x + 0.25)
                               .mapToObj(String::valueOf)
                               .collect(joining(", "));
        System.out.println("doubleRange: " + doubleRange);

        String boxed = IntStream.range(1, 3)
                                .boxed()
                                //!!! Integer::toString won't work because it is ambiguous
                                .map(Object::toString)
                                .collect(joining(", "));

        System.out.println("collect to string using boxed: " + boxed);

        Set<Integer> integerSet = IntStream.range(1, 3)
                                           .boxed()
                                           .collect(toSet());
        System.out.println("collect toSet must boxed: " + integerSet);

        System.out.print("range(1,1): [");
        IntStream.range(1, 1)
                 .forEach(System.out::print);
        System.out.println("]");

        count = IntStream.range(1, 1)
                         .count();
        System.out.println("range(1,1).count: " + count);

        OptionalDouble average = IntStream.range(1, 1)
                                          .average();
        System.out.println("range(1,1).average: " + average);

        System.out.print("rangeClosed(1,1): [");
        IntStream.rangeClosed(1, 1)
                 .forEach(System.out::print);
        System.out.println("]");

        count = IntStream.rangeClosed(1, 1)
                         .count();
        System.out.println("rangeCloses(1,1).count: " + count);

        average = IntStream.rangeClosed(1, 1)
                           .average();
        System.out.println("rangeClosed(1,1).average: " + average);
    }

    public static void aggregateNumericStreamMethods() {
        System.out.println(" ----- aggregateNumericStreamMethods");
        double sum = DoubleStream.iterate(0.0, x -> x + 0.125)
                                 .limit(30)
                                 .sum();
        System.out.println("DoubleStream sum: " + sum);

        OptionalDouble min = DoubleStream.iterate(0.0, x -> x + 0.125)
                                         .limit(30)
                                         .min();
        System.out.println("DoubleStream min: " + min);

        OptionalDouble max = DoubleStream.iterate(0.0, x -> x + 0.125)
                                         .limit(30)
                                         .max();
        System.out.println("DoubleStream max: " + max);

        OptionalDouble average = DoubleStream.iterate(0.0, x -> x + 0.125)
                                             .limit(30)
                                             .average();
        System.out.println("DoubleStream average: " + average);
    }

    public static void unboxing() {
        System.out.println(" ----- unboxing");
        Stream.of(141L, 42L, 55L)
              .mapToLong(Long::longValue)
              .forEach(System.out::println);

        // this doesn't do any sorting, the ordering is by encounter order
        Stream.of(123L, 412L, 55L)
              .mapToLong(Long::longValue)
              .forEachOrdered(System.out::println);

        // You can use d -> d, but Function::identity won't work
        Stream.of(1.45, 2.3, 4.6)
              .mapToDouble(d -> d)
              .forEach(System.out::println);
    }
}
