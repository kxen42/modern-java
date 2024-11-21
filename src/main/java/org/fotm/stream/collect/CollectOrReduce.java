package org.fotm.stream.collect;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class CollectOrReduce {

    private static final List<Student> students = StudentGenerator.createStudents();

    public static void main(String[] args) {
        usingReduce();
        usingCollect();
        findFirstStudentWithHighestGpa();
        sumNumberOfNotebooks();
        longestStudentName();
        shortestStudentName();

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

    public static void sumNumberOfNotebooks() {
        System.out.println(" ----- sumNumberOfNotebooks");
        Integer reduce = students.stream()
                                 .map(Student::getNoteBooks)
                                 .reduce(0, Integer::sum);
        System.out.println(reduce);

        System.out.println("------------------------- three arg reduce");
        // use this when working with parallel streams, the output for each should be equal
        Integer parallelStream = students.parallelStream()
                                         .map(Student::getNoteBooks)
                                         .reduce(0, Integer::sum, Integer::sum);
        System.out.println(parallelStream);
        System.out.println("expect equal values isEqual: " + parallelStream.equals(reduce));
    }

    public static void longestStudentName() {
        System.out.println(" ----- studentWithLongestName");
        // you  want a default value for min or max use an identity the MAX_VALUE/MIN_VALUE for identity
        Optional<Integer> reduce = students.stream()
                                           .map(student -> student.getName()
                                                                  .length())
                                           .filter(x -> false)
                                           .reduce(Integer::max);

        System.out.println("------------------------- not using identity");
        System.out.println(reduce.isPresent() ? reduce.get() : "empty stream");

        Integer usingIdentity = students.stream()
                                        .map(student -> student.getName()
                                                               .length())
                                        .filter(x -> false)
                                        .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println("------------------------- using identity");
        System.out.println(usingIdentity);
    }

    public static void shortestStudentName() {
        System.out.println(" ----- shortestStudentName");
// you  want a default value for min or max use an identity the MAX_VALUE/MIN_VALUE for identity
        Optional<Integer> reduce = students.stream()
                                           .map(student -> student.getName()
                                                                  .length())
                                           .filter(x -> false)
                                           .reduce(Integer::min);

        System.out.println("------------------------- not using identity");
        System.out.println(reduce.isPresent() ? reduce.get() : "empty stream");

        Integer usingIdentity = students.stream()
                                        .map(student -> student.getName()
                                                               .length())
                                        .filter(x -> false)
                                        .reduce(Integer.MAX_VALUE, Integer::min);

        System.out.println("------------------------- using identity");
        System.out.println(usingIdentity);
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
