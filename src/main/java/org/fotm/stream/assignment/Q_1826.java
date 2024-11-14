package org.fotm.stream.assignment;

import java.util.Optional;

/**
 * Assignment (QID 2.1826)
 * <p>
 * 5. Code a method {@code public static Optional<String> getGrade(int marks)}
 * <p>
 * a. in the method {@code getGrade}:<br>
 * i. declare an empty optional, typed for {@code String} called <i>grade</i><br>
 * ii. insert the following code:<br>
 * <pre>if (marks > 50) {
 *      grade = Optional.of("PASS");
 * } else {
 *      grade = Optional.of("FAIL");
 * }
 * </pre>
 * <p>
 * b. in {@code main()}:<br>
 * i. declare an {@code Optional}, typed for {@code String} named <i>grade1</i> which is initialised to the return
 * value of calling {@code getGrade(50)}<br>
 * ii. declare an {@code Optional}, typed for {@code String} named <i>grade2</i> which is initialised to the return
 * value of calling {@code getGrade(55)}<br>
 * iii. using {@code orElse()} on <i>grade1</i>, output the value of <i>grade1</i> or <i>"UNKNOWN"</i><br>
 * iv. {@code if(grade2.isPresent())} is {@code true}: use {@code ifPresent(Consumer)} to output the
 * contents of
 * <i>grade2</i>; if {@code false}, use {@code orElse()} to output the contents of <i>grade2</i> or <i>"Empty"</i>
 * <p>
 * v. <i><b>Notes:</b></i><br>
 * 1. {@code Optional} types are immutable.<br>
 * 2. <b>{@code Optional.of(null);}</b> throws {@code NullPointerException}<br>
 * 3. <b>{@code Optional.ofNullable(null);}</b> returns {@code Optional.empty}
 */
public class Q_1826 {

    public static void main(String[] args) {
        System.out.println(" ----- Q_1826");
        Optional<String> grade1 = getGrade(50);
        Optional<String> grade2 = getGrade(55);

        System.out.println(grade1.orElse("UNKNOWN"));

        if(grade2.isPresent()) {
            grade2.ifPresent(System.out::println);
        } else {
            System.out.println(grade2.orElse("Empty"));
        }
    }

    /**
     * Determine if it is a passing grade or failing grade.
     *
     * @param marks raw score
     * @return {@code "PASS"} or {@code "FAIL"}
     */
    public static Optional<String> getGrade(int marks) {
        Optional<String> grade = Optional.empty();
        if (marks > 50) {
            grade = Optional.of("PASS");
        } else {
            grade = Optional.of("FAIL");
        }
        return grade;
    }
}
