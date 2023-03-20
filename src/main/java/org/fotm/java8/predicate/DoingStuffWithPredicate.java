package org.fotm.java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * There's more to Predicate than just test().
 *
 * Its got
 * {@code
 * Predicate<T> and(Predicate<? super T>)   What's with the <? super T> ?
 * Predicate<T> isEqual(Object)
 * Predicate<T> negate()
 * Predicate<T> or(Predicate<? super T>).   What's with the <? super T> ?
 *
 * and boring old
 * boolean test(T)
 * }
 */
public class DoingStuffWithPredicate {
    public static void main(String[] args) {

        List<Car> cars = Arrays.asList(
        new Car("Audi", 1963),
                new Car("Audi", 2001),
                new Car("Audi", 2015),
                new Car("Ford", 1988),
                new Car("Ford", 1974),
                new Car("Ford", 2022)
        );

        Predicate<Car> audis = c -> c.make == "Audi";
        Predicate<Car> fords = c -> c.make == "Ford";
        Predicate<Car> oldCar = c -> c.year < 2000;

        System.out.println("Old Audis");
        cars.stream().filter(audis.and(oldCar)).forEach(System.out::println);

        System.out.println("Not old");
        cars.stream().filter(oldCar.negate()).forEach(System.out::println);

        System.out.println("Find my car");
        cars.stream().filter(Predicate.isEqual(new Car("Ford", 1974))).forEach(System.out::println);

        System.out.println("Or");
        cars.stream().filter(fords.or(oldCar)).forEach(System.out::println);
    }
}

class Car {
    String make;
    Integer year;

    public Car(String make, Integer year) {
        this.make = make;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return make.equals(car.make) && year.equals(car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", year=" + year +
                '}';
    }
}
