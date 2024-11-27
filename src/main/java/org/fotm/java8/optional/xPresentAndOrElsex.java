package org.fotm.java8.optional;

import org.fotm.model.Bike;
import org.fotm.model.Student;
import org.fotm.model.StudentGenerator;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Pretend we are using a database.
 * <p>
 * Personally I view any code that throws a NullPointerException. It indicates to me that not all scenarios were
 * considered, and that testing was sloppy.
 * <p>
 * I've worked on flight control systems software, and medical device software: both fields where unexpected
 * error conditions are not acceptable.
 * <p>
 * The use of {@code Optional} indicates to me that the programmer is at least considered the impact of receiving
 * a null value from a method.
 * <p>
 * {@code Optional} has a few methods that do the same thing that {@code Stream} methods with the same names do, but
 * instead of {@code Stream}s they return {@code Optional}s.
 * <p>
 * Samples from <em>dilipsundarraj1</em> are in the linked GitHub repo. I made some alterations to these to expand on something
 * I was curious about.
 *
 * @see <a href="https://github.com/dilipsundarraj1/java-8/tree/82b732c40011b2bbcaacac7200f332b4a45641bc/java-8/src/com/learnJava/optional">dilipsundarraj1/java-8</a>
 */
public class xPresentAndOrElsex {

    public static void main(String[] args) {

        System.out.println(getStudentName()); // boo  hiss you fail my code review
        findStudent(false);
        findStudent(true);
        orElse(false);
        orElse(true);
        orElseGet(false);
        orElseGet(true);
        orElseThrow(false);
        orElseThrow(true);
        ifPresent(false);
        ifPresent(true);
    }

    /**
     * Not using {@code Optional}
     *
     * @return student name or null
     */
    public static String getStudentName() {

        Student student = null;
        if (student != null) {
            return student.getName();
        } else {
            return null;
        }
    }


    /**
     * Demonstrates plain old {@code Optional.map}.
     * <p>
     * {@code Option.map(Function<T,U> mapper)} returns returns an {@code Optional<U>}. So here
     * we check if the value is present before doing something with it. The rule of thumb with optionals is to
     * <b>always</b> check if it is present.
     *
     * @param foundStudent flag to simulate finding student
     */
    public static void findStudent(boolean foundStudent) {
        System.out.println(" ----- findStudent");
        System.out.println("was student found? " + foundStudent);
        Optional<Student> optionalStudent = foundStudent ? Optional.ofNullable(StudentGenerator.studentSupplier.get()) : Optional.ofNullable(null);

        Optional<String> optionalStudentName = optionalStudent.map(Student::getName);
        if (optionalStudentName.isPresent()) {
            System.out.println("found " + optionalStudentName.get());
        } else {
            System.out.println("did not find Student got: " + optionalStudent.isEmpty());
        }
    }

    /**
     * Demonstrates {@code Optional.orElse}
     * <p>
     * {@code Optional.orElse(T)} returns and instance of {@code T} rather than an optional. It has to return
     * the same type as the optional it is called on.
     *
     * @param foundStudent flag to simulate finding the student
     */
    public static void orElse(boolean foundStudent) {
        System.out.println(" ----- orElse");
        System.out.println("was student found? " + foundStudent);
        Optional<Student> optionalStudent =
            foundStudent ? Optional.of(StudentGenerator.studentSupplier.get()) : Optional.ofNullable(null);

        String studentName = optionalStudent.map(Student::getName)
                                            .orElse("Bueller");

        if (foundStudent) {
            System.out.println("found " + studentName);
        } else {
            System.out.println("did not find Student got: " + studentName);
        }
    }

    /**
     * {@code Bike} is an optional field of {@code Student}. If the student doesn't have a bike,
     * return a new bike. {@code orElseGet} saves us from checking if the bike is present then
     * deciding to get the default. It's for the lazy programmer who doesn't want to see ugly code.
     *
     * @param isFound flag to simulate finding the student with a bike
     */
    public static void orElseGet(boolean isFound) {
        System.out.println(" ----- orElseGet");

        Bike bike;
        Student student;
        if (isFound) {
            student = StudentGenerator.studentSupplierWithBike.get();
            bike = student.getBike()
                          .orElseGet(StudentGenerator.supplierBike);
        } else {
            student = StudentGenerator.studentSupplier.get();
            bike = student.getBike()
                          .orElseGet(StudentGenerator.supplierBike);
            student.setBike(bike);
        }

        System.out.printf("did student have a bike? %s,%nwhat bike does student now have: %s%n", isFound, bike);
        System.out.println("student: " + student);
    }

    /**
     * Let's pretend little Timmy throws a fit if he doesn't have a bike. {@Optional.orElseThrow} gives
     * Timmy a chance to throw an exception if if doesn't have a bike.
     *
     * @param isFound flag to simulate finding the student with a bike
     */
    public static void orElseThrow(boolean isFound) {
        System.out.println(" ----- orElseThrow");

        if (isFound) {
            Student hasBike = StudentGenerator.studentSupplierWithBike.get();
            String model = hasBike.getBike()
                                  .map(Bike::getModel)
                                  .orElseThrow(RuntimeException::new);
            System.out.printf("%s has a %s%n", hasBike.getName(), model);
        } else {
            try {
                Student noBike = StudentGenerator.studentSupplier.get();
                String model = noBike.getBike()
                                     .map(Bike::getModel)
                                     .orElseThrow(() -> new RuntimeException("No fair!"));
                System.out.printf("%s has a %s%n", noBike.getName(), model);
            } catch (Exception e) {
                System.out.println("Little Timmy doesn't have a bike. " + e.getMessage());
            }
        }
    }

    /**
     * {@code Optional.ifPresent} will execute the {@code Consumer} action if the value is present; otherwise,
     * it does nothing. If the action it null it throws an NPE. This can save you from checking if it is present, and
     * it is ok if the value isn't present.
     *
     * @param isFound flag to simulate if value present
     */
    public static void ifPresent(boolean isFound) {
        System.out.println(" ----- ifPresent");
        System.out.println("is value present? " + isFound);

        Consumer<String> consumer = value -> System.out.println("value is " + value);

        if (isFound) {
            Optional<String> str = Optional.of("Boo");
            str.ifPresent(consumer);
        } else {
            Optional<String> str = Optional.ofNullable(null);
            str.ifPresent(consumer);
        }
    }
}
