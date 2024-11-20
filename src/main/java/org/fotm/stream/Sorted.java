package org.fotm.stream;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;
import org.fotm.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Sorted {

    private static final List<Student> students = StudentGenerator.createStudents();

    public static void main(String[] args) {
        sortUsersByAge();
        sortUsersByCityAgeLastNameFirstName();
        objectImplementsComparable();
        reverseObjectImplementsComparable();
        comparingField();
        comparingFieldReversed();
        complexComparator();
    }

    public static void sortUsersByAge() {
        System.out.println(" ----- sortUsersByAge");
        List<User> users = loadUsers();

        users.stream()
             .sorted(Comparator.comparing(User::getAge))
             .forEach(System.out::println);
    }

    public static void sortUsersByCityAgeLastNameFirstName() {
        System.out.println(" ----- sortUsersByCityAgeLastNameFirstName");
        List<User> users = loadUsers();

        users.stream()
             .sorted(Comparator.comparing(User::getCity)
                               .thenComparing(User::getAge)
                               .thenComparing(User::getLastName)
                               .thenComparing(User::getFirstName))
             .forEach(System.out::println);
    }

    private static List<User> loadUsers() {
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/Users.csv"))) {
            return lines
                .skip(1) // skip first line
                .map(l -> l.split(","))
                .map(arr -> User.builder()
                                .age(Integer.parseInt(arr[0]))
                                .city(arr[1])
                                .firstName(arr[3])
                                .lastName(arr[4])
                                .build())
                .toList();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public static void objectImplementsComparable() {
        System.out.println(" ----- objectImplementsComparable");
        students.stream()
                .sorted()
                .forEach(System.out::println);
    }

    public static void reverseObjectImplementsComparable() {
        System.out.println(" ----- reverseObjectImplementsComparable");
        students.stream()
                .sorted(Comparator.reverseOrder()) // can't use method reference, don't confuse with Collection version
                .forEach(System.out::println);
    }

    public static void comparingField() {
        System.out.println(" ----- comparingField");
        students.stream()
                .sorted(Comparator.comparing(Student::getGpa))
                .forEach(System.out::println);
    }

    public static void comparingFieldReversed() {
        System.out.println(" ----- comparingFieldReversed");
        students.stream()
                .sorted(Comparator.comparing(Student::getGpa)
                                  .reversed())
                .forEach(System.out::println);
    }

    public static void complexComparator() {
        System.out.println(" ----- complexComparator");
        students.stream()
                .sorted(Comparator.comparing(Student::getGender)
                                  .thenComparingDouble(Student::getGpa))
                .forEach(System.out::println);
    }
}
