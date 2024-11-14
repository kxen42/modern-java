package org.fotm.stream.assignment;

import org.fotm.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * These exercises are based on question id’s (QID’s) from Enthuware, the excellent Java Certification training tool.
 * Enthuware is an MCQ tool and this is why some answers in the video refer to “options”. However, I have left these
 * discussions in the solutions video as it enables me to discuss extra material, which aids in the learning experience.
 * <em>Note:</em> the QID’s are from Java 8 OCP question bank.
 * <p>
 * 4. There are several parts to this: (QID 2.1738)
 * <p>
 * <ul>
 * <li>a. Using 1, 2 and 3 create a List of Integers.</li>
 * <ul>
 * <li>i. Stream the list and calculate the sum, using the sum() method from IntStream.</li>
 * <li>ii. Stream the list again and calculate the maximum value, using the max() method from IntStream.</li>
 * </ul>
 * <li>b. Given the Person class (in the zip file), declare a List typed for Person with the following Person’s:</li>
 * <ul>
 * <li>i. “Alan”, “Burke”, 22</li>
 * <li>ii. “Zoe”, “Peters”, 20</li>
 * <li>iii. “Peter”, “Castle”, 29</li>
 * </ul>
 * Using the max(Comparator) from Stream, calculate the oldest person in the list.
 * </ul>
 * <ul>
 * <li>c. Using 10, 47, 33 and 23 create a List of Integers. Stream the list and using the following versions of reduce(), calculate the maximum value:</li>
 * <ul>
 * <li>i. Optional<T> reduce(BinaryOperator<T> accumulator)</li>
 * <li>ii. T reduce(T identity, BinaryOperator<T> accumulator)</li>
 * </ul>
 * </ul>
 */
public class Q2_1738 {
    public static void main(String[] args) {
        a();
        b();
        c();
    }

    public static void a() {
        System.out.println(" ----- a");
        List<Integer> integers = List.of(1, 2, 3);
        int sum = integers
            .stream()
            .mapToInt(Integer::intValue)    // unbox
            .sum();
        System.out.println("a.i. sum: " + sum);

        OptionalInt max = integers
            .stream()
            .mapToInt(Integer::intValue)
            .max();
        System.out.println("a.ii. max: " + max.orElse(Integer.MIN_VALUE));
    }

    public static void b() {
        System.out.println(" ----- b");
        List<Person> people = List.of(new Person("Alan", "Burke", 22),
                                      new Person("Zoe", "Peters", 20),
                                      new Person("Peter", "Castle", 29));

        Optional<Person> oldest = people.stream()
                                        .max(Comparator.comparing(Person::getAge));

        System.out.println("b. oldest person: " + oldest);
    }

    public static void c() {
        System.out.println(" ----- c");
        List<Integer> integers = List.of(10, 47, 33, 23);

        Optional<Integer> reduce1 = integers.stream()
                                            .reduce(Integer::max);

        System.out.println("c.i. " + reduce1);

        int reduce = integers.stream()
                             .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println("c.ii. " + reduce);
    }
}
