package org.fotm.stream;

import java.util.stream.Stream;

public class InfiniteStreams {
    public static void main(String[] args) {
        iterateWithLimit();
        generate();

    }

//    public static void iterateForever() {
//        Stream<Integer> infinite = Stream.iterate(2, n -> n + 2);
//    }

    public static void iterateWithLimit() {
        // finite stream of ordered numbers
        // 2, 4, 6, 8, 10, 12, 14, 16, 18, 20
        //
        // iterate(T seed, UnaryOperator<T> fn)
        //
        //   UnaryOperator is-a Function<T, T>
        //     T apply(T t)

        System.out.println(
        Stream
            .iterate(2, n -> n + 2)
            .limit(10)  // force it to end after 10 iterations
            .map(x -> Integer.toString(x))
            .reduce("", (x, y) -> x.isEmpty() ? y: String.join(", ", x, y))
        );


    }

    public static void generate() {
        // infinite stream of random unordered numbers
        // between 0..9 inclusive
        //
        //    Stream<T> generate(Supplier<T> s)
        //
        //      Supplier is a functional interface:
        //          T get()
        Stream<Integer> infStream = Stream.generate(() -> (int) (Math.random() * 10))
                                          .limit(10);

        String result = infStream
            .map(x -> Integer.toString(x))
            .reduce("", (x, y) -> x.isEmpty() ? y : String.join(", ", x, y));

        System.out.println(result);
    }
}
