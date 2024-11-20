package org.fotm.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Dilip.Sundarraj
 * @author kxen42
 * @see <a href="https://github.com/dilipsundarraj1/java-8/blob/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/data/StudentDataBase.java">Course StudentDatabase</a>
 */
public class StudentGenerator {

    public static Supplier<Student> StudentSupplier = () -> Student.builder()
                                                                   .activities(Arrays.asList("swimming", "basketball", "volleyball"))
                                                                   .gender("male")
                                                                   .gpa(4.0)
                                                                   .grade(Student.Grade.A)
                                                                   .gradeLevel(2)
                                                                   .name("Adam")
                                                                   .build();


    public static Optional<Student> getStudentWithBike() {

        Student student = Student.builder()
                                 .activities(Arrays.asList("swimming", "basketball", "volleyball"))
                                 .bike(Optional.of(new Bike("Client123", "Client123")))
                                 .gender("male")
                                 .gpa(4.0)
                                 .grade(Student.Grade.A)
                                 .gradeLevel(2)
                                 .name("Adam")
                                 .build();

        return Optional.of(student);
    }


    public static List<Student> createStudents() {
        return Arrays.asList(
            Student.builder()
                   .activities(Arrays.asList("swimming", "basketball", "volleyball"))
                   .gender("male")
                   .gpa(3.6)
                   .grade(Student.Grade.B)
                   .gradeLevel(2)
                   .name("Adam")
                   .noteBooks(10)
                   .build(),
            Student.builder()
                   .activities(Arrays.asList("swimming", "gymnastics", "soccer"))
                   .gender("female")
                   .gpa(3.8)
                   .grade(Student.Grade.A)
                   .gradeLevel(2)
                   .name("Jenny")
                   .noteBooks(11)
                   .build(),
            Student.builder()
                   .activities(Arrays.asList("swimming", "gymnastics", "aerobics"))
                   .gender("female")
                   .gpa(4.0)
                   .grade(Student.Grade.A)
                   .gradeLevel(3)
                   .name("Emily")
                   .noteBooks(12)
                   .build(),
            Student.builder()
                   .activities(Arrays.asList("swimming", "gymnastics", "soccer"))
                   .gender("male")
                   .gpa(4.0)
                   .grade(Student.Grade.A)
                   .gradeLevel(3)
                   .name("Dave")
                   .noteBooks(15)
                   .build(),
            Student.builder()
                   .activities(Arrays.asList("swimming", "dancing", "football"))
                   .gender("female")
                   .gpa(3.5)
                   .grade(Student.Grade.B)
                   .gradeLevel(4)
                   .name("Sophia")
                   .noteBooks(10)
                   .build(),
            Student.builder()
                   .activities(Arrays.asList("swimming", "basketball", "baseball", "football"))
                   .gender("female")
                   .gpa(3.9)
                   .grade(Student.Grade.A)
                   .gradeLevel(4)
                   .name("James")
                   .noteBooks(22)
                   .build(),
            Student.builder()
                   .activities(Arrays.asList("basketball", "baseball", "football"))
                   .gender("male")
                   .gpa(2.5)
                   .grade(Student.Grade.A)
                   .gradeLevel(4)
                   .name("Bubba")
                   .noteBooks(22)
                   .build(),
            Student.builder()
                   .activities(Collections.singletonList("football"))
                   .gender("male")
                   .gpa(2.0)
                   .grade(Student.Grade.D)
                   .gradeLevel(4)
                   .name("Elmore")
                   .noteBooks(22)
                   .build(),
            Student.builder()
                   .activities(Collections.emptyList())
                   .gender("male")
                   .gpa(1.5)
                   .grade(Student.Grade.F)
                   .gradeLevel(4)
                   .name("Gomer")
                   .noteBooks(22)
                   .build()
        );

    }

    public static void StudentSupplier() {}
}