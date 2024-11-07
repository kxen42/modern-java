package org.fotm.java17.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MainConsumer {

    public static void main(String[] args) {

        // If the lambda for the Consumer/BiConsumer returns a value, it is ignored

        // What can you do with this other than print or write to a logger?
        Consumer<String> easiestConsumerOnEarth = s -> System.out.println(s);
        easiestConsumerOnEarth.accept("the might consumer");

        Arrays.asList("Fred", "Barney")
              .forEach(easiestConsumerOnEarth);

        // Consumer has andThen(Consumer<T>)
        Consumer<String> withAndThen = easiestConsumerOnEarth.andThen(s -> System.out.println(s.toUpperCase()));
        withAndThen.accept("meddling kids");

        // stuff some values into a list without using collect()
        List<Integer> numbers = new ArrayList<>();
        Consumer<Integer> fx = x -> numbers.add(x);

        Arrays.asList(1, 2, 3)
              .forEach(fx);
        Arrays.asList(9, 8, 7)
              .forEach(fx);
        numbers.forEach(System.out::println);

        // BiConsumer
        BiConsumer<Integer, Integer> mult = (x, y) -> System.out.println(x * y);
        mult.accept(23, 4);

        // stuffing value into a map
        Map<String, String> map = new HashMap<>();
        BiConsumer<String, String> stuffMap = (key, value) -> map.put(key, value);

        stuffMap.accept("Fred", "Flintstone");
        stuffMap.accept("Barney", "Rubble");

        BiConsumer<String, String> printMap = (key, value) -> System.out.printf("key: %s, value: %s%n", key, value);

        // I did not expect this syntax would work - it figures out the two values for the BiConsumer
        map.forEach(printMap);
    }
}
