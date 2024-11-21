package org.fotm.stream.assignment;

import org.fotm.model.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Assignment (QID 2.1810)
 * <p>
 * 9. Given the {@code Person} class (in the zip file), declare a List typed for {@code Person} with the
 * following {@code Person}’s:
 * <p>
 * a. name="Bob", age=31<br>
 * b. name="Paul", age=32<br>
 * c. name="John", age=33<br>
 * <p>
 * Pipeline the following where the return type is {@code double}:
 * <ol>
 * <li>stream the people</li>
 * <li>filter the stream for {@code Person}’s whose age is &lt; 30</li>
 * <li>map to {@code int} primitives</li>
 * <li>calculate the average age.</li>
 * </ol>
 * <p>
 * This should generate a {@code NoSuchElementException}. Using {@code orElse()}, fix the pipeline (not the filter)
 * so that 0.0 is returned instead of an exception being generated.
 */
public class Q2_1810 {
    public static void main(String[] args) {
        List<Person> peeps = Arrays.asList(new Person(31, "Bob"),
                                           new Person(32, "Paul"),
                                           new Person(33, "John"));

        double average = peeps.stream()
                              .filter(person -> person.getAge() < 30)
                              .mapToInt(Person::getAge)
                              .average() // OptionalDouble at this point
                              //Exception in thread "main" java.util.NoSuchElementException: No value present
                              //    .getAsDouble()
                              .orElse(0.0);

        System.out.println(average);

    }
}
