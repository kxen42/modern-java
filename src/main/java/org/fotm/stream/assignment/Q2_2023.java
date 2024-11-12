package org.fotm.stream.assignment;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * These exercises are based on question id’s (QID’s) from Enthuware, the excellent Java Certification training tool.
 * Enthuware is an MCQ tool and this is why some answers in the video refer to “options”. However, I have left these
 * discussions in the solutions video as it enables me to discuss extra material, which aids in the learning experience.
 * <em>Note:</em> the QID’s are from Java 8 OCP question bank.
 * <p>
 *  1. Stream a list of int primitives between the range of 0 (inclusive) and 5 (exclusive).
 * Calculate and output the average.
 */
public class Q2_2023 {


    public static void main(String[] args) {

        IntStream exclusiveIntStream = IntStream.range(0, 5);

        OptionalDouble average = exclusiveIntStream.average();
        System.out.println("average is 2.0: " + (average.getAsDouble() == 2.0));

        IntStream closedIntStream = IntStream.rangeClosed(0, 5);
        average = closedIntStream.average();
        System.out.println("average is 2.5: " + (average.getAsDouble() == 2.5));

        IntStream zeroes = IntStream.range(0, 0);
        OptionalDouble average1 = zeroes.average();
        System.out.println("isPresent: " + average1.isPresent());
        System.out.println("isEmpty: " + average1.isEmpty());
        System.out.println("value is 0.0: " + average1.orElse(0));

    }
}
