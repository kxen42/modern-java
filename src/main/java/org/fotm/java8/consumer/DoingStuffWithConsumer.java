package org.fotm.java8.consumer;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.List;
import java.util.function.Consumer;

public class DoingStuffWithConsumer {

    public static void main(String[] args) {
        List<Student> students = StudentGenerator.createMultipleStudents();

        Consumer<Student> printStudent = System.out::println;
        Consumer<Student> printName = s -> System.out.println(s.getName()
                                                               .toUpperCase());

        Consumer<Student> printActivities = s -> System.out.println(String.join(", ", s.getActivities()));

        System.out.println(" ----- Print students");
        students.forEach(printStudent);

        System.out.println(" ----- Print names and activities");
        students.forEach(printName.andThen(printActivities)); // consumer chaining

        System.out.println(" ----- Alternate way to print names and activities ");
        students.forEach(
            s -> printName.andThen(printActivities) // more consumer chaining
                          .accept(s) // this kicks off the chain of consumer's like Hungarian notation
        );
    }
}
