package org.fotm.stream.collect;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toSet;

public class PartitioningBy {
    public static void main(String[] args) {
        evenOrOdd();
        startsWithA();
    }

    public static void evenOrOdd() {
        Map<Boolean, List<Integer>> collect = IntStream.range(0, 20)
                                                       .boxed()
                                                       .collect(partitioningBy(x -> x % 2 == 0));

        System.out.println(collect);
    }

    public static void startsWithA() {
        Map<Boolean, Set<String>> collect = Stream.of("apple", "carrot", "Audi", "U2", "ABBA", "Ford", "apple", "apple")
                                                  .collect(partitioningBy(s -> s.matches("[aA].*"), toSet()));

        //{false=[Ford, U2, carrot], true=[apple, Audi, ABBA]}
        System.out.println(collect);
    }
}
