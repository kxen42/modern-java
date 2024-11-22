package org.fotm.stream.assignment;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Assignment (QID 2.2024)
 * <p>
 * 12. There are two parts:
 * <p>
 * a. Generate a {@code DoubleStream} using the {@code of()} method consisting of the numbers 0, 2 and 4. Note
 * that this stream is a stream of primitives and not a stream of types. Filter in <em>odd</em> numbers only
 * and <em>sum</em> the remaining stream. You should get 0.
 * <p>
 * b. Using 1.0 and 3.0, generate a stream of {@code Double}’s. Map them to primitive double’s. Filter in
 * <em>even</em> numbers only and calculate the <em>average</em>. Output the result without running the risk of generating an
 * exception.
 */
public class Q2_2024 {
    public static void main(String[] args) {
        a();
        b();
    }

    public static void a() {
        System.out.println(" ----- a");
        DoubleStream doubleStream = DoubleStream.of(0, 2, 4);

        double sum = doubleStream.filter(x -> x % 2 != 0)
                                 .sum();

        System.out.println(sum);
    }

    public static void b() {
        System.out.println(" ----- b");
        Stream<Double> doubles = Stream.of(1.0, 3.0);
        DoubleStream doubleStream = doubles.mapToDouble(Double::doubleValue);
        OptionalDouble average = doubleStream.filter(x -> x % 2 == 0)
                                             .average();

        System.out.println("average isEmpty? " + average.isEmpty());

    }
}
