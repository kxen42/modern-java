package org.fotm.java8.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * There's more to Predicate than just test().
 * <p>
 * Its got
 * {@code
 * Predicate<T> and(Predicate<? super T>)   What's with the <? super T> ?
 * Predicate<T> isEqual(Object)
 * Predicate<T> negate()
 * Predicate<T> or(Predicate<? super T>).   What's with the <? super T> ?
 * <p>
 * and boring old
 * boolean test(T)
 * }
 */
public class DoingStuffWithPredicates {
    public static void main(String[] args) {
        List<Car> cars = createCars();
        singlePredicate(cars);
        System.out.println("--------------------------------------------------");
        biPredicate(cars);
        System.out.println("--------------------------------------------------");
        doFizzbuzz();
        System.out.println("--------------------------------------------------");
        predicateInFunction(cars);
        System.out.println("--------------------------------------------------");
    }

    private static void biPredicate(List<Car> cars) {
        System.out.println("Use BiPredicate, BiConsumer, Consumer");
        System.out.println("High mileage Audis");
        BiPredicate<String, Double> yearMileage = (make, miles) -> make.equals(make) && miles > 80000;

        BiConsumer<Car, List<String>> spewDefects = (car, defects) -> System.out.println(car.getMake() + " | " + car.getYear() + " | " + defects);

        Consumer<Car> carConsumer = car -> {
            if (yearMileage.test(car.getMake(), car.getMileage())) {
                spewDefects.accept(car, car.getDefects());
            }
        };

        cars.forEach(carConsumer);
    }

    private static void singlePredicate(List<Car> cars) {
        System.out.println("Use Predicate");
        Predicate<Car> audis = c -> c.getMake() == "Audi";
        Predicate<Car> fords = c -> c.getMake() == "Ford";
        Predicate<Car> oldCar = c -> c.getYear() < 2000;

        System.out.println("Old Audis");
        cars.stream()
            .filter(audis.and(oldCar))
            .forEach(System.out::println);

        System.out.println("\nNewer Audis");
        cars.stream()
            .filter(oldCar.negate())
            .forEach(System.out::println);

        System.out.println("\nFind my car");
        cars.stream()
            .filter(Predicate.isEqual(new Car("Ford", 1974, 200000.0)))
            .forEach(System.out::println);

        System.out.println("\nFords or old cars");
        cars.stream()
            .filter(fords.or(oldCar))
            .forEach(System.out::println);
    }

    private static void predicateInFunction(List<Car> cars) {
        System.out.println("'nesting' Predicate in BiFunction");
        System.out.println("Find rusty cars");
        Predicate<Car> rusty = (car) -> car.getDefects()
                                           .contains("rust");
        BiFunction<Car, Predicate<Car>, Car> carFunction = (car, predicate) -> {
            if (predicate.test(car)) {
                return car;
            }
            return null;
        };

        cars.stream()
            .filter(c -> carFunction.apply(c, rusty) != null)
            .forEach(System.out::println);
    }

    private static void doFizzbuzz() {
        System.out.println("Fizzbuzz allowing blank lines using Predicate");
        System.out.println("What's a programming interview with out this little gem?");
        fizzbuzz(6);
        fizzbuzz(10);
        fizzbuzz(15);
        fizzbuzz(17);


        System.out.println("Fizzbuzz without blank lines using Predicate");
        System.out.println("What's a programming interview with out this little gem?");
        fizzbuzzNoBlankLines(6);
        fizzbuzzNoBlankLines(10);
        fizzbuzzNoBlankLines(15);
        fizzbuzzNoBlankLines(17);
    }

    static void fizzbuzz(Integer num) {
        Predicate<Integer> divisibleBy3 = x -> x % 3 == 0;
        Predicate<Integer> divisibleBy5 = x -> x % 5 == 0;

        if (divisibleBy3.test(num))
            System.out.print("fizz");
        if (divisibleBy5.test(num))
            System.out.print("buzz");
        System.out.println();
    }

    static void fizzbuzzNoBlankLines(Integer num) {
        // if there is no match print no match
        Predicate<Integer> divisibleBy3 = x -> x % 3 == 0;
        Predicate<Integer> divisibleBy5 = x -> x % 5 == 0;

        if (divisibleBy3.or(divisibleBy5)
                        .negate()
                        .test(num)) return;

        if (divisibleBy3.test(num))
            System.out.print("fizz");
        if (divisibleBy5.test(num))
            System.out.print("buzz");

        System.out.println();
    }

    private static List<Car> createCars() {
        List<Car> cars = Arrays.asList(
            new Car("Audi", 1963, 250000.9),
            new Car("Audi", 2001, 90000.5),
            new Car("Audi", 2015, 50150.4),
            new Car("Ford", 1988, 16124.3),
            new Car("Ford", 1974, 200000.0),
            new Car("Ford", 2022, 76000.2)
        );
        cars.get(0)
            .getDefects()
            .addAll(Arrays.asList("rust", "cracked windshield", "no seatbelts", "flat front tires"));
        cars.get(1)
            .getDefects()
            .addAll(Arrays.asList("torn driver side seat", "AM radio not working", "front springs kaput"));
        cars.get(2)
            .getDefects()
            .addAll(Arrays.asList("chip in windshield"));
        cars.get(3)
            .getDefects()
            .addAll(Arrays.asList("shift know loose", "short in steering column", "spontaneous combustion", "rust"));
        cars.get(4)
            .getDefects()
            .addAll(Arrays.asList("rust", "missing knobs", "Maverick"));
        return cars;
    }
}

class Car {
    private String make;
    private Integer year;

    private Double mileage;

    private List<String> defects = new ArrayList<>();

    public Car(String make, Integer year, Double mileage) {
        this.make = make;
        this.year = year;
        this.mileage = mileage;
    }

    public String getMake() {
        return make;
    }

    public Integer getYear() {
        return year;
    }

    public Double getMileage() {
        return mileage;
    }

    public List<String> getDefects() {
        return defects;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, year, mileage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return make.equals(car.make) && year.equals(car.year) && mileage.equals(car.mileage);
    }

    @Override
    public String toString() {
        return "Car{" +
            "make='" + make +
            ", year=" + year +
            ", mileage=" + mileage +
            ", defects=[" +
            String.join(", ", defects) +
            "]'}'";
    }
}
