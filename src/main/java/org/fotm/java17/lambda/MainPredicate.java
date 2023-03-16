package org.fotm.java17.lambda;

import java.util.function.Predicate;

interface Evaluate<T> {
    boolean isTrue(T t);
}

public class MainPredicate {

    public static void main(String[] args) {

        Evaluate<Integer> withInteger = i -> i < 0;
        Evaluate<String> withString = str -> str.length() > 0;

        // using a FunctionalInterface
        System.out.println(withInteger.isTrue(-2));
        System.out.println(withInteger.isTrue(42));
        System.out.println(withString.isTrue(""));
        System.out.println(withString.isTrue("boo"));

        // explicit definition of Predicate
        Predicate<Integer> predicate = i -> i < 0;
        System.out.println(predicate.test(-1));

        // the magic of Generics
        System.out.println(check(42, i -> i % 2 == 0));
        System.out.println(check("boo", s -> s.contains("o")));
    }

    // Passing a Predicate / lambda as a method argument
    public static <T> boolean check(T t, Predicate<T> lambda) {
        return lambda.test(t);
    }
}
