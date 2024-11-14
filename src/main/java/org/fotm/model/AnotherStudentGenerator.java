package org.fotm.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class AnotherStudentGenerator {

    public static Supplier<AnotherStudent> AnotherStudentSupplier = () -> AnotherStudent.builder()
                                                                                    .activities(Arrays.asList("swimming", "basketball", "volleyball"))
                                                                                    .gender("male")
                                                                                    .gpa(4.0)
                                                                                    .gradeLevel(2)
                                                                                    .name("Adam")
                                                                                    .build();

    public static Optional<AnotherStudent> getOptionalAnotherStudent() {

        AnotherStudent anotherStudent = AnotherStudent.builder()
                                                      .activities(Arrays.asList("swimming", "basketball", "volleyball"))
                                                      .bike(Optional.of(new Bike("Client123", "Client123")))
                                                      .gender("male")
                                                      .gpa(4.0)
                                                      .gradeLevel(2)
                                                      .name("Adam")
                                                      .build();

        return Optional.of(anotherStudent);
    }


    public static List<AnotherStudent> createMoreAnotherStudents() {
        return Arrays.asList(
            AnotherStudent.builder()
                          .activities(Arrays.asList("swimming", "basketball", "volleyball"))
                          .gender("male")
                          .gpa(3.6)
                          .gradeLevel(2)
                          .name("Adam")
                          .noteBooks(10)
                          .build(),

            AnotherStudent.builder()
                          .activities(Arrays.asList("swimming", "gymnastics", "soccer"))
                          .gender("female")
                          .gpa(3.8)
                          .gradeLevel(2)
                          .name("Jenny")
                          .noteBooks(11)
                          .build(),
            AnotherStudent.builder()
                          .activities(Arrays.asList("swimming", "gymnastics", "aerobics"))
                          .gender("female")
                          .gpa(4.0)
                          .gradeLevel(3)
                          .name("Emily")
                          .noteBooks(12)
                          .build(),
            AnotherStudent.builder()
                          .activities(Arrays.asList("swimming", "gymnastics", "soccer"))
                          .gender("male")
                          .gpa(4.0)
                          .gradeLevel(3)
                          .name("Dave")
                          .noteBooks(15)
                          .build(),
            AnotherStudent.builder()
                          .activities(Arrays.asList("swimming", "dancing", "football"))
                          .gender("female")
                          .gpa(3.5)
                          .gradeLevel(4)
                          .name("Sophia")
                          .noteBooks(10)
                          .build(),
            AnotherStudent.builder()
                          .activities(Arrays.asList("swimming", "basketball", "baseball", "football"))
                          .gender("female")
                          .gpa(3.9)
                          .gradeLevel(4)
                          .name("James")
                          .noteBooks(22)
                          .build()
        );

    }
}