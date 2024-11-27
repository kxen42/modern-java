package org.fotm.java8.optional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Demo {@code Optional.ifPresentOrElse}.
 * <p>
 * {@code Optional} method {@code public void ifPresentOrElse(Consumer<? super T> action,
 * Runnable emptyAction)} was introduced in Java 9. Its purpose is to allow you to take one action if the value
 * and to take another action of it is not present. A common use case is to log a message when the value is not
 * present.
 */
public class OptionalIfPresentOrElse {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        ifPresentOrElse();
        System.out.println("final emptyAction counter: " + counter.get());
    }

    public static void ifPresentOrElse() {
        System.out.println(" ----- ifPresentOrElse");

        Stream<Optional<Integer>> optionalStream = IntStream.rangeClosed(0, 10)
                                                            .boxed()
                                                            .map(x -> {
                                                                if (x % 2 == 0) {
                                                                    return Optional.ofNullable(x);
                                                                } else {
                                                                    return Optional.empty();
                                                                }
                                                            });

        optionalStream.forEach(x ->
                                   x.ifPresentOrElse(
                                       (value) -> System.out.println("was present value: " + value),
                                       () -> {
                                           counter.getAndIncrement();
                                           System.err.println("value was not present, current emptyAction count: " + counter.get());
                                       }));
    }
}
