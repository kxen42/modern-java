package org.fotm.stream.collect;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Counting {
    private static final List<Car> cars = CarGenerator.createCars();

    public static void main(String[] args) {
        collectorsCounting();
        streamCount();
        peeking();
        noPeeking();
    }

    public static void collectorsCounting() {
        System.out.println(" ----- collectorsCounting");
        // when to use Collectors.counting
        Long ford = cars.stream()
                        .filter(c -> c.getMake()
                                      .equals("Ford"))
                        .collect(Collectors.counting());
        System.out.println("number of Fords: " + ford);
    }

    public static void streamCount() {
        System.out.println(" ----- streamCount");
        // when to use Collectors.counting
        long count = cars.stream()
                         .filter(c -> c.getMake()
                                       .equals("Ford"))
                         .count();
        System.out.println("number of Fords: " + count);
    }

    /**
     * {@code Collectors.counting()} always processes the pipeline unlike {@code Stream.count()}.
     */
    public static void peeking() {
        System.out.println(" ----- peeking");
        Long count = cars.stream()
                         .peek(System.out::println)
                         .collect(Collectors.counting());
        System.out.println("count: " + count);
    }

    /**
     * {@code Stream.count()}
     * <p>
     * Returns the count of elements in this stream.  This is a special case of
     * a reduction and is equivalent to:
     * <pre>{@code
     *     return mapToLong(e -> 1L).sum();
     * }</pre>
     *
     * @apiNote An implementation may choose to not execute the stream pipeline (either
     * sequentially or in parallel) if it is capable of computing the count
     * directly from the stream source.  In such cases no source elements will
     * be traversed and no intermediate operations will be evaluated.
     * Behavioral parameters with side-effects, which are strongly discouraged
     * except for harmless cases such as debugging, may be affected.  For
     * example, consider the following stream:
     * <pre>{@code
     *     List<String> l = Arrays.asList("A", "B", "C", "D");
     *     long count = l.stream().peek(System.out::println).count();
     * }</pre>
     * The number of elements covered by the stream source, a {@code List}, is
     * known and the intermediate operation, {@code peek}, does not inject into
     * or remove elements from the stream (as may be the case for
     * {@code flatMap} or {@code filter} operations).  Thus the count is the
     * size of the {@code List} and there is no need to execute the pipeline
     * and, as a side-effect, print out the list elements.
     */
    public static void noPeeking() {
        System.out.println(" ----- noPeeking");
        // won't process pipeline so you won't see the peek.
        long count = cars.stream()
                         .peek(System.out::println)
                         .count();

        System.out.println(count);

    }
}
