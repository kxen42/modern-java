package org.fotm.stream.collect;

import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CollectingAndThen {

    private static final List<User> users = UserGenerator.createUsers();

    public static void main(String[] args) {
        toCollectingAndThenToUnmodifiableList();
        wrapASet();
        toCollectingAndThenToUnmodifiableMap();
        changingTypeDrastically();
        postPostoneProcessing();
        twoWaysToFindLongestName();
    }

    /**
     * public static &lt;T,A,R,RR&gt; Collector&lt;T,A,RR&gt; collectingAndThen(Collector&lt;T,A,R&gt; downstream,
     * Function&lt;R,RR&gt; finisher)
     * <p>
     * Adapts a Collector to perform an additional finishing transformation. For example, one could adapt the toList()
     * collector to always produce an immutable list with:
     * <p>
     * <pre>
     * List<String> list = people.stream().collect(
     * collectingAndThen(toList(),
     * Collections::unmodifiableList));
     * </pre>
     * <p>
     * Convert to unmodifiable Set
     * <pre>
     * collectingAndThen(Collectors.toSet(),
     *                   Collections::unmodifiableSet)
     * </pre>
     */
    public static void toCollectingAndThenToUnmodifiableList() {
        System.out.println(" ----- toCollectingAndThenToUnmodifiableList");
        List<String> collect = Stream.of("a", "b", "c")
                                     .collect(
                                         collectingAndThen(toList(),
                                                           Collections::unmodifiableList));

        System.out.println(collect);

        try {
            collect.add("z");
        } catch (UnsupportedOperationException e) {
            System.out.println("\tgot expected UnsupportedOperationException");
        }
    }

    /**
     * Wrap a set to make it unmodifiable.
     * <p>
     * Convert to unmodifiable Set
     * <pre>
     *      collectingAndThen(Collectors.toSet(),
     *                        Collections::unmodifiableSet)
     *     </pre>
     */
    public static void wrapASet() {

        Set<Long> usignCollectorsToUnmodifiableSet = LongStream.of(8, 16, 32, 64, 128, 1024, 2048)
                                                               .boxed()
                                                               .collect(Collectors.toUnmodifiableSet());
        try {
            usignCollectorsToUnmodifiableSet.add(42L);
        } catch (UnsupportedOperationException e) {
            System.out.println("\tgot expected UnsupportedOperationException");
        }

        Set<Long> usingCollectingAndThen = LongStream.of(8, 16, 32, 64, 128, 1024, 2048)
                                                     .boxed()
                                                     .collect(collectingAndThen(
                                                         Collectors.toSet(),
                                                         Collections::unmodifiableSet));
        try {
            usingCollectingAndThen.add(42L);
        } catch (UnsupportedOperationException e) {
            System.out.println("\tgot expected UnsupportedOperationException");
        }
    }

    /**
     * collectingAndThen(toMap,
     * Collections::unmodifiableMap))
     */
    public static void toCollectingAndThenToUnmodifiableMap() {
        System.out.println(" ----- toCollectingAndThenToUnmodifiableMap");
        Map<String, String> collect = Stream.of(new String[][]{
                                                {"a", "1"},
                                                {"b", "2"},
                                                {"c", "42"},
                                            })
                                            .collect(
                                                collectingAndThen(toMap(twoDarray -> twoDarray[0], twoDarray -> twoDarray[1]),
                                                                  Collections::unmodifiableMap));

        System.out.println(collect);

        try {
            collect.put("z", "47");
        } catch (UnsupportedOperationException e) {
            System.out.println("\tgot expected UnsupportedOperationException");
        }
    }

    /**
     * collectingAndThen(Collector, Boolean)
     */
    public static void changingTypeDrastically() {
        System.out.println(" ----- changingTypeDrastically");

        // collectingAndThen(Collector, Boolean)
        // Empty Stream
        Boolean result = Stream.of()
                               .collect(collectingAndThen(toList(), List::isEmpty));

        System.out.println("\tresult is boolean, is it empty? " + result);

        // collectingAndThen(Collector, Boolean)
        result = Stream.of(1, 2, 3)
                       .collect(collectingAndThen(toList(), List::isEmpty));

        System.out.println("\tresult is boolean, is it empty? " + result);
    }

    public static void postPostoneProcessing() {
        System.out.println(" ----- postPostoneProcessing");

        // It is the Collector.mapping that processes all of the elements
        // before the collecting is done.

        String longestName = users.stream()
                                  .collect(collectingAndThen(
                                               // Encounter all the User objects
                                               // Map them to their first names
                                               // Collect those names in a list
                                               mapping(
                                                   User::getFirstName,
                                                   toList()
                                               ),
                                               // Stream those names again
                                               // Find the longest name
                                               // If not available, return "?"
                                               l -> {
                                                   return l
                                                       .stream()
                                                       .collect(Collectors.maxBy(
                                                           comparing(String::length)
                                                       ))
                                                       .orElse("?");
                                               }
                                           )
                                  );
        System.out.println(longestName);
    }

    public static void twoWaysToFindLongestName() {
        System.out.println(" ----- twoWaysToFindLongestName");
        sortByLengthThenReverseList();
        // faster because there is no sorting and reversing
        usingCollectingAndThen();

    }

    private static void sortByLengthThenReverseList() {
        String longestName = users
            .stream()
            .map(User::getFirstName)
            .sorted(Comparator.comparing(String::length)
                              .reversed())
            .findFirst()
            .orElse("?");
        System.out.println(longestName);
    }

    private static void usingCollectingAndThen() {

        String longestName = users.stream()
                                   .map(User::getFirstName)
                                   .collect(collectingAndThen(
                                       Collectors.maxBy(Comparator.comparing(String::length)),
                                       s -> s.orElse("?")));
        System.out.println(longestName);
    }
}
