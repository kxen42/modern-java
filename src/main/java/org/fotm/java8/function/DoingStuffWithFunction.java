package org.fotm.java8.function;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class DoingStuffWithFunction {

    private static final List<Student> students = StudentGenerator.createMultipleStudents();

    private static final BiPredicate<Student, Integer> isStudentInGradeLevel = (s, i) -> i.equals(s.getGradeLevel());
    private static final Function<List<Student>, Map<String, Student.Grade>> getStudentsForGradeLevel =
        ((studentList) -> {
            return studentList.stream()
                              .filter(s -> isStudentInGradeLevel.test(s, 3))
                              .collect(toMap(
                                  Student::getName,
                                  Student::getGrade,
                                  (s1, s2) -> s1,
                                  TreeMap::new
                              ));
        });

    // TODO: how can we do this with Collectors.toMap?
    // using Map.forEach
    private static final Function<Map<String, Student.Grade>, Map<String, Student.Grade>> changeNameCase =
        (m -> {
            Map<String, Student.Grade> result = new HashMap<>();
            m.forEach((key, value) -> result.put(key.toUpperCase(), value));
            return result;
        });


    private static final BiConsumer<String, Student.Grade> spewStudentAndGrade = (s, g) -> System.out.println("student: " + s + ", grade: " + g);

    public static void main(String[] args) {
        findThirdGraders();
        functionAndThen();
        functionAndCompose();
    }

    public static void findThirdGraders() {
        System.out.println(" ----- findThirdGraders");

        Map<String, Student.Grade> gradeMap = getStudentsForGradeLevel.apply(students);
        gradeMap.forEach(spewStudentAndGrade);
    }

    public static void functionAndThen() {
        System.out.println(" ----- findThirdGraders");

        Map<String, Student.Grade> gradeMap = getStudentsForGradeLevel
            .andThen(changeNameCase)
            .apply(students);
        gradeMap.forEach(spewStudentAndGrade);
    }

    /**
     * Given fx = Function&lt;T,U&gt; and fy = Function&lt;U,T&gt;, then
     * {@code fx.andThen(fy)} is the same as {@code fy.compose(fx)}.
     */
    public static void functionAndCompose() {
        System.out.println(" ----- functionAndCompose");

        Function<String, String> fx = x -> "[" + x + "]";
        Function<Integer, String> fy = Integer::toHexString;

        System.out.println(fy.andThen(fx)
                             .apply(42));

        System.out.println(fx.compose(fy)
                             .apply(42));

    }
}
