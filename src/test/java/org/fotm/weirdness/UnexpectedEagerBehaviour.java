package org.fotm.weirdness;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UnexpectedEagerBehaviour {

    /**
     * Inspired by <a href=https://www.baeldung.com/java-stream-of-and-intstream-range>Understanding the Difference Between Stream.of() and IntStream.range()</a>
     * <p>
     * Note the use of
     * <pre>
     *     "object::instance method" method reference
     *     IntStream.range()
     *     Stream.of()
     *     Optional<T>
     *     OptionalInt
     *     peek() to stuff things in a ordered List
     * </pre>
     */
    @Test
    public void peekEffects_NoSort() {
        Stream<Integer> streamOf = Stream.of(1, 2, 3, 4, 5);
        IntStream intStreamByRange = IntStream.range(1, 6);

        List<Integer> streamOfResult = new ArrayList<>();
        List<Integer> intStreamResult = new ArrayList<>();

        Optional<Integer> a = streamOf.peek(streamOfResult::add)
                                      .findFirst();

        OptionalInt b = intStreamByRange.peek(intStreamResult::add)
                                        .findFirst();

        System.out.println("streamOfResult");
        streamOfResult.forEach(System.out::println);
        System.out.println("intStreamResult");
        intStreamResult.forEach(System.out::println);

        // no surprises
        assertThat(a.orElse(Integer.MAX_VALUE)).isEqualTo(1);
        assertThat(b.orElse(Integer.MAX_VALUE)).isEqualTo(1);

        assertThat(streamOfResult).containsExactly(1);
        assertThat(intStreamResult).containsExactly(1);
    }

    /**
     * peek() is run on all of the elements.
     * <p>
     * For Stream.of() it must look at all elements to do the sort.
     * <p>
     * IntStream is already sorted so the {@code sorted()} is a noop and only one element needs to be looked at to {@code findFirst].
     */
    @Test
    public void peekEffects_WithSort() {
        Stream<Integer> streamOf = Stream.of(2, 4, 1, 3, 5);
        IntStream intStreamByRange = IntStream.range(1, 6);

        List<Integer> streamOfResult = new ArrayList<>();
        List<Integer> intStreamResult = new ArrayList<>();

        Optional<Integer> a = streamOf.peek(streamOfResult::add) // processes all elements
                                      .sorted() // creates a sorted stream
                                      .findFirst();

        OptionalInt b = intStreamByRange.peek(intStreamResult::add)
                                        .sorted() // noop for IntStream
                                        .findFirst();

        System.out.println("streamOfResult");
        streamOfResult.forEach(System.out::println);

        System.out.println("intermediate sorted stream from streamOf");
        ArrayList<Integer> bob = new ArrayList<>();
        Stream.of(2, 4, 1, 3, 5)
              .peek(bob::add) // must process all elements before it can sort
              .sorted() // creates a sorted stream
              .forEach(System.out::println);

        System.out.println("intStreamResult");
        intStreamResult.forEach(System.out::println);

        System.out.println("collect streamOf sorted");
        List<Integer> fred = Stream.of(2, 4, 1, 3, 5)
                                   .sorted() // creates a sorted stream
                                   .toList();

        // no surprises
        assertThat(a.orElse(Integer.MAX_VALUE)).isEqualTo(1);
        assertThat(b.orElse(Integer.MAX_VALUE)).isEqualTo(1);

        assertThat(streamOfResult).containsExactly(2, 4, 1, 3, 5);
        assertThat(bob).containsExactly(2, 4, 1, 3, 5);
        assertThat(intStreamResult).containsExactly(1);

        assertThat(fred).containsExactly(1, 2, 3, 4, 5);
    }
}
