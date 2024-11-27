package org.fotm.java8.optional;

import org.fotm.model.Bike;
import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Examples of {@code map}, {@code flatMap}, {@code filter}, {@code or}.
 * <p>
 * {@code Optional} has a few methods that do the same thing that {@code Stream} methods with the same names do, but
 * instead of {@code Stream}s they return {@code Optional}s.
 * <p>
 * Samples from <em>dilipsundarraj1</em> are in the linked GitHub repo. I made some alterations to these to expand on something
 * I was curious about.
 *
 * @see <a href="https://github.com/dilipsundarraj1/java-8/tree/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/optional">dilipsundarraj1/java-8</a>
 */
public class OptionalMapAndFilter {

    public static void main(String[] args) {
        optionalOr(false);
        optionalOr(true);
        optionalFilter(false);
        optionalFilter(true);
        optionalMap(false);
        optionalMap(true);
        optionalFlatMap(false);
        optionalFlatMap(true);
    }

    /**
     * Demo {@code Optional.or()}.
     * <p>
     * The difference between {@code or(Supplied T)} and {@code orElse(T)}, is that  {@code or} takes a {@code Supplier<T>}.
     * It was introduced in Java 9 so some must've found a need for it. Difference between or and the orElse's is that {@code or}
     * returns a value wrapped in {@code Optional} where the others return a plain value.
     *
     * @param isFound flag to simulate value is found
     */
    public static void optionalOr(boolean isFound) {

        Optional<String> notEmpty = Optional.of("Barney");
        Optional<String> isEmpty = Optional.empty();
        Supplier<Optional<String>> defaultSupplier = () -> Optional.of("Fred");

        if (isFound) {
            Optional<String> result = notEmpty.or(defaultSupplier);
            System.out.printf("isFound: %s, result %s%n", isFound, result);
        } else {
            Optional<String> result = isEmpty.or(defaultSupplier);
            System.out.printf("isFound: %s, result %s%n", isFound, result);
        }

    }

    public static void optionalFilter(boolean isFound) {
        System.out.println(" ----- filter");

        Student student;
        if (isFound) {
            student = StudentGenerator.studentSupplierWithBike.get();
        } else {
            student = StudentGenerator.studentSupplier.get();
        }

        student.getBike()
               .filter(b -> b.getModel()
                             .equals("Schwinn"))
               .ifPresentOrElse(
                   (value) -> System.out.printf("%s is a Schwinn%n", value.getName()),
                   () -> student.setBike(new Bike("Fred", "Trek"))
               );

        System.out.println(student);
    }

    /**
     * {@code Optional.flatMap} does not wrap the value if is already wrapped in {@code Optional}.
     * {@code flatMap} is used to get to the value of something already wrapped in an {@code Optional}.
     *
     * @param isFound
     */
    public static void optionalFlatMap(boolean isFound) {
        System.out.println(" ----- optionalFlatMap");

        Optional<Student> student;
        if (isFound) {
            student = Optional.of(StudentGenerator.studentSupplier.get());
        } else {
            student = Optional.of(StudentGenerator.studentSupplierWithBike.get());
        }
        Optional<Bike> bike = student.flatMap(Student::getBike);

        System.out.println("Show difference between map and flatMap: " + bike);
    }

    /**
     * {@code Optional.map} always wraps value in {@code Optional} even if it is already wrapped in {@code Optional}.
     * {@code map} lets you wrap something in {@code Optional}.
     *
     * @param isFound
     */
    public static void optionalMap(boolean isFound) {
        System.out.println(" ----- optionalMap");

        Student student;
        if (isFound) {
            student = StudentGenerator.studentSupplierWithBike.get();
        } else {
            student = StudentGenerator.studentSupplier.get();
        }

        Optional<String> optionalString = student.getBike()
                                                 .map(Bike::getName);

        System.out.printf("Expect empty optional? %s, what is the name: %s%n", !isFound, optionalString);

        Optional<Student> stu = Optional.of(StudentGenerator.studentSupplier.get());
        Optional<Optional<Bike>> bike = stu.map(Student::getBike);

        System.out.println("Show difference between map and flatMap: " + bike);
    }
}
