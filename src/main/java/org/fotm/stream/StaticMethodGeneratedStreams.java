package org.fotm.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Using:
 * <pre>
 *     Array.stream()
 *     List.of().stream()
 *     Stream.of()
 * </pre>
 */
public class StaticMethodGeneratedStreams {

    private static final int[] ints = {200, 201, 400, 403, 404};

    public static void main(String[] args) {
        arrayStream();
        arraysAsList();
        listOf();
        streamOf();
    }

    /**
     * Arrays.stream()
     * <p>
     */
    public static void arrayStream() {

        System.out.println("Arrays.stream() " + Arrays.stream(ints)
                                                      .count());
    }

    /**
     * Arrays.asList()
     * <p>
     * Creates a <em>wrapper</em> around the array. The elements can be changed
     * in the List, and the array will be changed. The size of the array is fixed.
     * Attempts to change the size throw {@code UnsupportedOperationException}.
     * <p>
     * Except for the size, the list is mutable.
     * <p>
     * When working with objects it allows nulls.
     */
    public static void arraysAsList() {
        // result is one, what gives?
        System.out.println("Arrays.asList().stream() " + Collections.singletonList(ints)
                                                                    .stream()
                                                                    .count());
    }

    /**
     * List.of()
     * <p>
     * Creates an unmodifiable List that is a <em>copy</em> of the array. Attempts to change
     * the elements throw {@code UnsupportedOperationException}.
     * <p>
     * Does not allow null values, a null value will cause {@code NullPointerException}.
     */
    public static void listOf() {
        // result is one, what gives?
        System.out.println("List.of().stream() " + List.of(ints)
                                                       .stream()
                                                       .count());
    }

    /**
     * Stream.of()
     * <p>
     * Creates sequential {@code Stream} from one to many values.
     */
    public static void streamOf() {
        Stream<String> flintstones = Stream.of("Wilma", "Fred", "Betty", "Barney");

        System.out.println("Stream.of sorts list " + flintstones
            .reduce("", (x, y) -> x.isEmpty() ? y : String.join(", ", x, y)));

    }
}


