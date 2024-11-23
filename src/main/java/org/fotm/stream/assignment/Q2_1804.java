package org.fotm.stream.assignment;

import org.fotm.model.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Assignment (QID 2.1804)
 * <p>
 * This is missing a description.<br>
 * {@code {A=[S1, S2], C=[S3]}}
 */
public class Q2_1804 {
    public static final List<Student> students = Arrays.asList(
        new Student("S1", Student.Grade.A),
        new Student("S2", Student.Grade.A),
        new Student("S3", Student.Grade.C));

    public static void main(String[] args) {
        groupingByGrade();
    }

    private static void groupingByGrade() {
        System.out.println(" ----- groupingByGrade");
        Map<Student.Grade, List<String>> grouping =
            students.stream()
                    .collect(
                        // Function gives us the key in the Map
                        // The values in the Map are the entries that match the key.
                        Collectors.groupingBy(Student::getGrade,
                                              Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(grouping); //
    }
}