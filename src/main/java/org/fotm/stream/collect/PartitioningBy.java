package org.fotm.stream.collect;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toSet;

public class PartitioningBy {

    private static final List<Car> cars = CarGenerator.createCars();

    public static void main(String[] args) {
        evenOrOdd();
        startsWithA();
        groupingByIntoPartioningBy();
    }

    /**
     * Partition into evens and odds.
     * <pre>
     * {false=[1, 3, 5, 7, 9, 11, 13, 15, 17, 19], true=[0, 2, 4, 6, 8, 10, 12, 14, 16, 18]}
     * </pre>
     */
    public static void evenOrOdd() {
        System.out.println(" ----- evenOrOdd");
        Map<Boolean, List<Integer>> collect = IntStream.range(0, 20)
                                                       .boxed()
                                                       .collect(partitioningBy(x -> x % 2 == 0));

        System.out.println(collect);
    }

    /**
     * Partition into words starting with [aA]; otherwise, a different list.
     * <pre>
     * {false=[Ford, U2, carrot], true=[apple, Audi, ABBA]}
     * </pre>
     */
    public static void startsWithA() {
        System.out.println(" ----- startsWithA");
        Map<Boolean, Set<String>> collect = Stream.of("apple", "carrot", "Audi", "U2", "ABBA", "Ford", "apple", "apple")
                                                  .collect(partitioningBy(s -> s.matches("[aA].*"), toSet()));
        System.out.println(collect);
    }


    /**
     * <pre>
     *  make: Ford
     * 	    Low Mileage Cars
     * 		    mileages: 16124.3
     * 	    Hig Mileage Cars
     * 		    mileages: 200000.0
     * </pre>
     */
    public static void groupingByIntoPartioningBy() {
        System.out.println(" ----- groupingByIntoPartioningBy");
        Map<String, Map<Boolean, List<Car>>> collect = cars.stream()
                                                           .collect(
                                                               groupingBy(Car::getMake,
                                                                          partitioningBy(c -> c.getMileage() > 20000.0
                                                                          )));

        collect.forEach((make, hiLoMileageCars) -> {

            System.out.println("make: " + make);
            hiLoMileageCars.forEach((isHigh, cars) -> {
                                        if (!cars.isEmpty()) {
                                            if (isHigh)
                                                System.out.println("\tHig Mileage Cars");
                                            else
                                                System.out.println("\tLow Mileage Cars");
                                            String mileagesByMake = cars.stream()
                                                                        .sorted(Comparator.comparing(Car::getMileage))
                                                                        .map(c -> String.valueOf(c.getMileage()))
                                                                        .collect(Collectors.joining(", "));
                                            System.out.println("\t\tmileages: " + mileagesByMake);
                                        }
                                    }
            );
        });
    }
}