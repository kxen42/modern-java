package org.fotm.java8.predicate;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;


/**
 * Experimenting with flavors of Predicate and its various methods.
 * <p>
 * There's more to Predicate than just {@code test(T)}, there are primitive predicates
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Predicate.html">Predicate</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/DoublePredicate.html">DoublePredicate</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/IntPredicate.html">IntPredicate</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/LongPredicate.html">LongPredicate</a>
 */
public class DoingStuffWithPredicate {

    public static final Consumer<Car> printCar = System.out::println;

    private static boolean isFord(Car car) {
        return car.getMake()
                  .equals("Ford");
    }

    public static void main(String[] args) {
        List<Car> cars = CarGenerator.createCars();
        singlePredicate(cars);
        biPredicate(cars);
        doFizzbuzz();
        predicateInFunction(cars);
        predicateAsMethodReference();
    }

    private static void biPredicate(List<Car> cars) {
        System.out.println(" ----- biPredicate");
        System.out.println("High mileage Audis");
        BiPredicate<String, Double> yearMileage = (make, miles) -> miles > 80000;

        // BiConsumer
        BiConsumer<Car, List<String>> spewDefects = (car, defects) -> System.out.println(car.getMake() + " | " + car.getYear() + " | " + defects);

        // Consumer
        Consumer<Car> carConsumer = car -> {
            if (yearMileage.test(car.getMake(), car.getMileage())) {
                spewDefects.accept(car, car.getDefects());
            }
        };

        cars.forEach(carConsumer);
    }

    private static void singlePredicate(List<Car> cars) {
        System.out.println(" -----singlePredicate");
        Predicate<Car> audis = c -> Objects.equals(c.getMake(), "Audi");
        Predicate<Car> fords = c -> Objects.equals(c.getMake(), "Ford");
        Predicate<Car> oldCar = c -> c.getYear() < 2000;

        // Predicate chaining with 'and'
        System.out.println("Old Audis");
        cars.stream()
            .filter(audis.and(oldCar))
            .forEach(printCar);

        // Predicate 'negate'
        System.out.println("\nNewer Audis");
        cars.stream()
            .filter(oldCar.negate())
            .forEach(printCar);

        // Predicate 'isEqual'
        System.out.println("\nFind my car");
        cars.stream()
            .filter(Predicate.isEqual(cars.get(4)))
            .forEach(printCar);

        // Predicate 'or'
        System.out.println("\nFords or old cars");
        cars.stream()
            .filter(fords.or(oldCar))
            .forEach(printCar);
    }

    private static void predicateInFunction(List<Car> cars) {
        System.out.println(" ----- predicateInFunction");
        System.out.println("'nesting' Predicate in BiFunction");
        System.out.println("Find rusty cars");

        // Predicate
        Predicate<Car> rusty = (car) -> car.getDefects()
                                           .contains("rust");

        // BiFunction<T, Predicate<T>>
        BiFunction<Car, Predicate<Car>, Car> carFunction = (car, predicate) -> {
            if (predicate.test(car)) {
                return car;
            }
            return null;
        };

        cars.stream()
            .filter(c -> carFunction.apply(c, rusty) != null)
            .forEach(printCar);
    }

    private static void doFizzbuzz() {
        System.out.println(" ----- doFizzbuzz");
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
        System.out.println(" ----- fizzbuzz for " + num);
        IntPredicate divisibleBy3 = x -> x % 3 == 0;
        IntPredicate divisibleBy5 = x -> x % 5 == 0;

        if (divisibleBy3.test(num))
            System.out.print("fizz");
        if (divisibleBy5.test(num))
            System.out.print("buzz");
        System.out.println();
    }

    static void fizzbuzzNoBlankLines(Integer num) {
        // if there is no match print no match
        IntPredicate divisibleBy3 = x -> x % 3 == 0;
        IntPredicate divisibleBy5 = x -> x % 5 == 0;

        // Predicate chaining or.negate.test
        if (divisibleBy3.or(divisibleBy5)
                        .negate()
                        .test(num)) return;

        if (divisibleBy3.test(num))
            System.out.print("fizz");
        if (divisibleBy5.test(num))
            System.out.print("buzz");

        System.out.println();
    }

    public static void predicateAsMethodReference() {
        System.out.println(" ----- predicateAsMethodReference");
        Predicate<Car> predicateAsMethodRef = DoingStuffWithPredicate::isFord;

        List<Car> cars = CarGenerator.createCars();

        cars.stream()
            .filter(predicateAsMethodRef)
            .forEach(printCar);

    }

}