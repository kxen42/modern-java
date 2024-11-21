package org.fotm.stream.collect;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class CollectOrReduceWithString {
    public static void main(String[] args) {
        usingReduce();
        usingCollect();
        findFirstStudentWithHighestGpa();
    }

    public static void usingReduce() {
        System.out.println(" ----- usingReduce");
        String reduce = Stream.of("a", "b", "c")
                              .parallel()
                              .reduce("",
                                      (n, s) -> n + s,
                                      (n1, n2) -> n1 + n2);
        System.out.println(reduce);

    }

    /**
     * TODO: See how usingReduce this compare for performance in Java 17+
     * This is not guaranteed to return 'abc', it did when running with the debugger.
     */
    public static void usingCollect() {
        System.out.println(" ----- usingCollect");
        StringBuilder collect = Stream.of("a", "b", "c")
                                      .parallel()
                                      .collect(StringBuilder::new,
                                               StringBuilder::append,
                                               StringBuilder::append);
        System.out.println(collect);

    }

    public static void findFirstStudentWithHighestGpa() {
        System.out.println(" ----- findFirstStudentWithHighestGpa");
        List<Student> students = StudentGenerator.createStudents();

        BinaryOperator<Student> studentWithHigherGpa = (s1, s2) -> (s1.getGpa() >= s2.getGpa()) ? s1 : s2;

        Student sorted = students.stream()
                                 .sorted()
                                 .peek(System.out::println)
                                 .reduce(new Student(),
                                         studentWithHigherGpa,
                                         (x, y) -> x);
        System.out.println("------------------------- sorted first student with highest GPA");
        System.out.println(sorted);
        System.out.println("-------------------------");

        Student notSorted = students.stream()
                                    .peek(System.out::println)
                                    .reduce(new Student(),
                                            studentWithHigherGpa,
                                            (x, y) -> x);
        System.out.println("------------------------- not sorted first student with highest GPA");
        System.out.println(notSorted);

    }
}
