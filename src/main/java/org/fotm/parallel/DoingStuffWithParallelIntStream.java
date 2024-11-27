package org.fotm.parallel;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Rule of Thumb - always check performance before using parallel streams. For small number of elements, running
 * in parallel can be much slower.
 */
public class DoingStuffWithParallelIntStream {

    private static final Supplier<Integer> sequentialSum = () -> IntStream.range(0, 1000000)
                                                                          .sum();
    private static final Supplier<Integer> parallelSum = () -> IntStream.range(0, 1000000)
                                                                        .parallel()
                                                                        .sum();

    public static void main(String[] args) {

        System.out.println("Available processors: " + Runtime.getRuntime()
                                                             .availableProcessors());
        sumIntStreams();
        sequentialCarDefects();
        parallelCarDefects(); // performs worse than sequential
    }

    private static void sumIntStreams() {
        System.out.println(" ----- sumIntStreams");
        System.out.println("for range 1000000 parallel takes long");
        timeTrial(20, sequentialSum);
        timeTrial(20, parallelSum);
    }

    public static void timeTrial(int iterations, Supplier<Integer> supplier) {
        for (int i = 0; i <= iterations; i++) supplier.get(); // warmup
        long start = System.currentTimeMillis();
        for (int i = 0; i <= iterations; i++) supplier.get();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("duration milliseconds ^");
    }

    public static void sequentialCarDefects() {
        System.out.println(" ----- sequentialCarDefects");
        Instant start = Instant.now();
        List<Car> cars = CarGenerator.createCars();
        cars.stream()
            .flatMap(car -> car.getDefects()
                               .stream())
            .sorted()
            .distinct()
            .collect(Collectors.toList()); // deliberately don't use set
        System.out.println("duration (microsecond): " + start.until(Instant.now(), ChronoUnit.MICROS));
    }

    public static void parallelCarDefects() {
        System.out.println(" ----- parallellCarDefects");
        Instant start = Instant.now();
        List<Car> cars = CarGenerator.createCars();
        cars.parallelStream()
            .flatMap(car -> car.getDefects()
                               .stream())
            .sorted()
            .distinct()
            .collect(Collectors.toList()); // deliberately don't use set
        System.out.println("duration (microsecond): " + start.until(Instant.now(), ChronoUnit.MICROS));
    }
}