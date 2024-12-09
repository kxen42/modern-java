package org.fotm.java8.interfaces;

import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class CollectionDefaultInterfaceMethods {

    static Consumer<Student> consumer = s -> System.out.println(s.getName());

    public static void main(String[] args) {
        listDefaultSort();
        listDefaultReplaceAll();
        sortByName();
        sortByGpa();
        sortByGradeLevelGpaName();
    }

    public static void listDefaultSort() {
        System.out.println(" -----listDefaultSort ");
        List<String> cantHaveNulls = Arrays.asList("zum", "aus", "bei", "mit", "neben", "");
        System.out.println(cantHaveNulls);

        cantHaveNulls.sort(Comparator.naturalOrder());
        System.out.println(cantHaveNulls);

        cantHaveNulls.sort(Comparator.reverseOrder());
        System.out.println(cantHaveNulls);

        // Without nullsFirst / nullsLast you'd get NPE
        List<String> hasNulls = Arrays.asList(null, "zum", "aus", "bei", "mit", "neben", "");
        System.out.println(hasNulls);

        hasNulls.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(hasNulls);

        hasNulls.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(hasNulls);

    }

    public static void listDefaultReplaceAll() {
        System.out.println(" ----- listDefaultReplaceAll");
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println(list);

        list.replaceAll(i -> i * i);
        System.out.println(list);

    }

    public static void sortByName() {
        System.out.println(" ----- sortByName");
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
        List<Student> students = StudentGenerator.createStudents();

        students.sort(nameComparator);

        students.forEach(consumer);
    }

    public static void sortByGpa() {
        System.out.println(" ----- sortByGpa");
        Comparator<Student> gpaComparator = Comparator.comparingDouble(Student::getGpa);
        List<Student> students = StudentGenerator.createStudents();

        students.sort(gpaComparator.reversed());

        students.forEach(System.out::println);
    }

    public static void sortByGradeLevelGpaName() {
        System.out.println(" ----- sortByNameGradeLevelGpa");
        Comparator<Student> byName = Comparator.comparing(Student::getName);
        Comparator<Student> byGradeLevel = Comparator.comparingInt(Student::getGradeLevel);
        Comparator<Student> byGpa = Comparator.comparingDouble(Student::getGpa);

        List<Student> students = StudentGenerator.createStudents();

        System.out.println("------------------------- byGradeLevel.thenComparing(byGpa.thenComparing(byName))");
        students.sort(byGradeLevel.thenComparing(byGpa.thenComparing(byName)));

        students.forEach(System.out::println);
        System.out.println("------------------------- byGradeLevel.thenComparing(byGpa).thenComparing(byName)");
        students = StudentGenerator.createStudents();
        students.sort(byGradeLevel.thenComparing(byGpa)
                                  .thenComparing(byName));

        students.forEach(System.out::println);
    }
}
