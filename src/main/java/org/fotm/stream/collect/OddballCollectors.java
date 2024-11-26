package org.fotm.stream.collect;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;
import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;
import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

public class OddballCollectors {

    private static final List<Car> cars = CarGenerator.createCars();
    private static final List<User> users = UserGenerator.createUsers();
    private static final List<Student> students = StudentGenerator.createStudents();

    public static void main(String[] args) {
        summingInt();
        summarizingStatistics();
        teeingMinAndMax();
        teeingAndFiltering();
        teeingToSingleString();
        groupingByIntoMayBy();
        groupingByNameToGetStudentByMinGpa();
    }

    /**
     * Comparing {@code summingX} to {@code mapToX.sum}.
     * <p>
     * Prints "summingInt: 258, mapToInt.sumL: 258"
     */
    public static void summingInt() {
        System.out.println(" ----- summingInt");
        Integer sum1 = users.stream()
                            .collect(Collectors.summingInt(User::getAge));

        int alternateSum = users.stream()
                                .mapToInt(User::getAge)
                                .sum();

        System.out.printf("summingInt: %d, mapToInt.sumL: %d%n", sum1, alternateSum);
    }

    /**
     * Using {@code Stream.iterate(BigDecimal)} and {@code summarizingX} to get {@code XSummaryStatistics}.
     * <p>
     * Prints
     * <pre>
     * doubleSummaryStatistics: DoubleSummaryStatistics{count=20, sum=210.000000, min=1.000000, average=10.500000, max=20.000000}
     * intSummaryStatistics: IntSummaryStatistics{count=20, sum=210, min=1, average=10.500000, max=20}
     * longSummaryStatistics: LongSummaryStatistics{count=20, sum=210, min=1, average=10.500000, max=20}
     * </pre>
     */
    public static void summarizingStatistics() {
        System.out.println(" ----- summarizingStatistics");

        Stream<BigDecimal> bigDecimalStream = Stream
            .iterate(BigDecimal.ONE, accumulator -> accumulator.add(BigDecimal.ONE))
            .limit(20);

        DoubleSummaryStatistics doubleSummaryStatistics = bigDecimalStream.collect(Collectors.summarizingDouble(BigDecimal::doubleValue));

        System.out.println("doubleSummaryStatistics: " + doubleSummaryStatistics);

        bigDecimalStream = Stream
            .iterate(BigDecimal.ONE, accumulator -> accumulator.add(BigDecimal.ONE))
            .limit(20);

        IntSummaryStatistics intSummaryStatistics = bigDecimalStream.collect(Collectors.summarizingInt(BigDecimal::intValue));

        System.out.println("intSummaryStatistics: " + intSummaryStatistics);

        bigDecimalStream = Stream
            .iterate(BigDecimal.ONE, accumulator -> accumulator.add(BigDecimal.ONE))
            .limit(20);

        LongSummaryStatistics longSummaryStatistics = bigDecimalStream.collect(Collectors.summarizingLong(BigDecimal::longValue));

        System.out.println("longSummaryStatistics: " + longSummaryStatistics);

    }

    /**
     * The Collectors.teeing() method is useful when we want to simultaneously process a stream in two different ways
     * and then combine their results. If not using the teeing() method, we would have processed the stream two times,
     * teeing() allows to process in a single statement.
     * <p>
     * During stream processing, every element passed to the resulting collector is processed by both downstream
     * collectors, then their results are merged using the specified merge function into the final result.
     * <p>
     * Please note that teeing() function helps perform a certain task in a single step. We can surely perform the given
     * task in two steps if we do not use the teeing() function. It’s just a helper function that helps in reducing the
     * verbosity.
     *
     * <pre>{@code
     * public static Collector teeing​ (Collector downstream1,
     * 						Collector downstream2,
     * 						BiFunction merger);
     * }</pre>
     * <p>
     * Prints "{MIN=1963, MAX=2015}"
     */
    public static void teeingMinAndMax() {
        System.out.println(" ----- teeingMinAndMax");
        HashMap<String, Integer> map = cars.stream()
                                           .collect(Collectors.teeing(minBy(Comparator.comparing(Car::getYear)),
                                                                      maxBy(Comparator.comparing(Car::getYear)),
                                                                      (optionalMinCar, optionalMaxCar) -> {
                                                                          HashMap<String, Integer> result = new HashMap<>();
                                                                          result.put("MIN", optionalMinCar.isPresent() ? optionalMinCar.get()
                                                                                                                                       .getYear()
                                                                              : -1);
                                                                          result.put("MAX", optionalMaxCar.isPresent() ? optionalMaxCar.get()
                                                                                                                                       .getYear()
                                                                              : -1);
                                                                          return result;
                                                                      }));

        System.out.println(map);
    }

    /**
     * Get list and number of students with grades over 3.5.
     * <p>
     * Using {@code Collectors.teeing()} to create a {@code Map}.
     * <p>
     * Using {@code Collectors}:
     * <pre>{@code
     * teeing()
     * filtering()
     * toList()
     * count()
     * joining()
     * }</pre>
     * <p>
     * Prints
     * <pre>
     * Found 5 students with GPAs > 3.5. They are:
     * [
     * name: Adam, gpa: 3.600000, gradeLevel: 2
     * name: Jenny, gpa: 3.800000, gradeLevel: 2
     * name: Emily, gpa: 4.000000, gradeLevel: 3
     * name: Dave, gpa: 4.000000, gradeLevel: 3
     * name: James, gpa: 3.900000, gradeLevel: 4
     * ]
     * </pre>
     */
    public static void teeingAndFiltering() {
        System.out.println(" ----- teeingAndFiltering");
        HashMap<String, Object> results = students.stream()
                                                  .collect(Collectors.teeing(
                                                      Collectors.filtering(s -> s.getGpa() > 3.5, Collectors.toList()),
                                                      Collectors.filtering(s -> s.getGpa() > 3.5, Collectors.counting()),
                                                      (c1, c2) -> {
                                                          HashMap<String, Object> map = new HashMap<>();
                                                          String collect = c1.stream()
                                                                             .map(student -> String.format("name: %s, gpa: %f, gradeLevel: %s", student.getName(), student.getGpa(), student.getGradeLevel()))
                                                                             .collect(Collectors.joining("\n", "[\n", "\n]"));
                                                          map.put("students list", collect);
                                                          map.put("number of students", c2);
                                                          return map;
                                                      }
                                                  ));
        System.out.printf("Found %s students with GPAs > 3.5. They are:%n%s%n", results.get("number of students"),
                          results.get("students list"));
    }

    /**
     * Using {@code Collectors.teeing} to get a single object result.
     * <p>
     * Prints "average: 121255.220000, number of cars: 5"
     */
    public static void teeingToSingleString() {
        System.out.println(" ----- teeingToSingleString");
        String result = cars.stream()
                            .collect(Collectors.teeing(
                                Collectors.averagingDouble(Car::getMileage),
                                Collectors.counting(),
                                (c1, c2) -> String.format("average: %f, number of cars: %d", c1, c2)
                            ));

        System.out.println(result);
    }

    /**
     *
     */
    public static void groupingByIntoMayBy() {
        System.out.println(" ----- groupingByIntoMayBy");
        Map<Integer, Optional<Student>> studentsWithHighestGpa = students.stream()
                                                                         .collect(groupingBy(Student::getGradeLevel,
                                                                                             maxBy(Comparator.comparing(Student::getGpa))));

        studentsWithHighestGpa.forEach((gradeLevel, student) -> {
            System.out.println("gradeLevel: " + gradeLevel + ", gpa: " + (student.isPresent() ? student.get()
                                                                                                       .getGpa() : "no max gpa"));
        });
    }

    /**
     * Using {@code groupingBy} to get the student names and {@code Collectors.minBy} to get the lowest and using
     * {@code collectingAndThen(minBy, Optional::get)} to avoid dealing with {@code Optional} later.
     */
    public static void groupingByNameToGetStudentByMinGpa() {
        System.out.println(" ----- groupingByNameToGetStudentByMinGpa");
        Map<Integer, Student> studentsWithLowedGpas = students.stream()
                                                              .collect(groupingBy(Student::getGradeLevel,
                                                                                  collectingAndThen(minBy(Comparator.comparing(Student::getGpa)), Optional::get)
                                                              ));
        studentsWithLowedGpas.forEach((gradeLevel, student) -> {
            System.out.println("gradeLevel: " + gradeLevel + ", gpa: " + student.getGpa());
        });
    }
}
