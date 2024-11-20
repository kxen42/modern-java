package org.fotm.java8.consumer;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.List;
import java.util.function.Consumer;

public class DoingStuffWithConsumer {

    public static void main(String[] args) {
        List<Student> students = StudentGenerator.createStudents();

        Consumer<Student> studentConsumer = System.out::println;
        Consumer<Student> nameConsumer = s -> System.out.println(s.getName()
                                                                  .toUpperCase());
        Consumer<Student> gradeConsumer = s -> System.out.print("grade: " + s.getGrade() + ", activities: ");
        Consumer<Student> activitiesConsumer = s -> System.out.println(String.join(", ", s.getActivities()));

        System.out.println(" ----- Print students");
        students.forEach(studentConsumer);

        System.out.println(" ----- Print names and activities");
        students.forEach(nameConsumer.andThen(activitiesConsumer)); // consumer chaining

        System.out.println(" ----- Alternate way to print names and activities ");
        students.forEach(
            s -> nameConsumer.andThen(activitiesConsumer) // more consumer chaining
                             .accept(s) // this kicks off the chain of consumer's like Hungarian notation
        );

        System.out.println(" ----- Print the A and B students activities");
        students.forEach(s -> {
            if (s.getGrade()
                 .equals(Student.Grade.A) || s.getGrade()
                                              .equals(Student.Grade.B)) {
                // The syntax makes it easy to forget that this is running on a Student
                nameConsumer.andThen(gradeConsumer)
                            .andThen(activitiesConsumer)
                            .accept(s);
            }
        });
    }
}
