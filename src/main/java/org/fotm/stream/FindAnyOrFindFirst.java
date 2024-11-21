package org.fotm.stream;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindAnyOrFindFirst {

    private static Set<String> words1;
    private static Set<String> words2;

    /**
     * Experiment with {@code Stream.findFirst} and {@code Stream.anyFind}.
     * <p>
     * Try running it a few times to see what {@code FindAnyOrFindFirst.findFirst} does.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        findFirst();
        findFirst();
        findAny();
        findAny();
    }

    /**
     * The documentation says the {@code Stream.findFirst()}:<br>
     * Returns an {@code Optional} describing the first element of this stream, or an empty {@code Optional}
     * if the stream is empty. If the stream has <em>no encounter order</em>, then any element may be returned.
     * <p>
     * Demonstrating the <em>no encounter order</em> is hard to do because the order depends on the JDK
     * and can change depending on the JVM implementation; it depends on what the JVM decides to do. Even using {@code HashSet} won't work.
     * The {@code BaseStream.unordered()} does not affect the encounter order either.
     * <p>
     * <b>I'm not sure this covers the <em>no encounter order</em> case.</b> This always picks the first item
     * in the {@code HashSet}. The data generator does produce values that are not in the same order, and the
     * first item in each test data set is picked - not picked based on natural order of integers.
     */
    public static void findFirst() {
        System.out.println(" ----- findFirst");

        generateTestData();

        Optional<String> first1 = words1.stream()
                                        .findFirst();

        Optional<String> first2 = words2.stream()
                                        .findFirst();

        words2.retainAll(words1);
        System.out.println(words1);
        System.out.println(words2);
        System.out.println("words1 findFirst: " + first1 + ", words2 findFirst: " + first2);

    }

    /**
     * The documentation for {@code Stream.findAny} says:<br>
     * The behavior of this operation is explicitly nondeterministic; it is free to select any element in the stream.
     * This is to allow for maximal performance in parallel operations; the cost is that multiple invocations on the
     * same source may not return the same result. (If a stable result is desired, use {@code findFirst()} instead.)
     */
    public static void findAny() {
        System.out.println(" ----- findAny");

        generateTestData();

        Optional<String> first1 = words1.stream()
                                        .findAny();


        Optional<String> first2 = words2.stream()
                                        .findAny();
        words2.retainAll(words1);

        System.out.println(words1);
        System.out.println(words2);
        System.out.println("words1 findAny: " + first1 + ", words2 findAny: " + first2);
    }


    private static void generateTestData() {
        words1 = Stream.generate(() -> (int) (Math.random() * 10))
                       .limit(10)

                       .map(String::valueOf)
                       .collect(Collectors.toUnmodifiableSet());

        Set<String> tmp1 = new HashSet<>(200);
        for (String str : words1) {
            tmp1.add(str);
        }
        words1 = tmp1;

        words2 = Stream.generate(() -> (int) (Math.random() * 100))
                       .limit(100)
                       .map(String::valueOf)
                       .collect(Collectors.toUnmodifiableSet());
        Set<String> tmp = new HashSet<>(200);
        for (String str : words2) {
            tmp.add(str);
        }
        words2 = tmp;
        words2.retainAll(words1);

    }
}
