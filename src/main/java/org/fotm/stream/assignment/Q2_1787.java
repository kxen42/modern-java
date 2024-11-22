package org.fotm.stream.assignment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Assignment (QID 2.1787)
 * <p>
 * 3. Generate a {@code Stream<List<String>>} using the
 * {@code Stream.of(Arrays.asList(“a”, “b”), Arrays.asList("d", "c"), Arrays.asList(“a”, “c”))} method call.
 * <p>
 * Filter the stream so that only list’s that contain “c” make it through the filter.
 * <p>
 * Flatten the {@code Stream<List<String>>} to a {@code Stream<String>} using the {@code flatMap()} operation.
 * <p>
 * <em>Note</em> that {@code flapMap()} states in the API “Each mapped stream is closed after its contents
 * have been placed into this [new] stream.”. Use {@code forEach()} to output the new stream.
 */
public class Q2_1787 {
    public static void main(String[] args) {
        Stream<List<String>> streamOfLists = Stream.of(Arrays.asList("a", "b"),
                                                       Arrays.asList("d", "c"),
                                                       Arrays.asList("a", "c"));

        Stream<List<String>> c = streamOfLists.filter(l -> l.contains("c"));
        Stream<String> stringStream = c.flatMap(Collection::stream);
        stringStream.distinct()
                    .forEach(System.out::println);
    }
}
