package org.fotm.stream;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class InfiniteStreams {
    public static void main(String[] args) {
        iterateWithLimit();
        generate();
        fibonacci();

    }

//    public static void iterateForever() {
//        Stream<Integer> infinite = Stream.iterate(2, n -> n + 2);
//    }

    public static void iterateWithLimit() {
        System.out.println(" ----- iterateWithLimit");
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
                .reduce("", (x, y) -> x.isEmpty() ? y : String.join(", ", x, y))
        );

    }

    public static void generate() {
        System.out.println(" ----- generate");
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

    /**
     * Given X[0] = 0 and X[1] = 1
     * Then
     *      X[n] = X[n-1] + X[n-2]
     * For n > 1.
     * <br>
     *     x[0] = 0
     *     x[1] = 1
     * n = 2    X[2] = X[1] + X[0] = 1
     * n = 3    X[3] = X[2] + X[1] = 2
     */
    public static void fibonacci() {
        System.out.println(" ----- fibonacci");
        String fibonacciSequence = Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]}) // Stream<int[]>
                               .limit(15) // 15 int arrays in Stream<int[]>
                               .map(arr -> Integer.toString(arr[0])) // Stream<String> the accumulator has the each fibonacci number
                               .collect(joining(", "));
        System.out.println(fibonacciSequence);


    }
}
