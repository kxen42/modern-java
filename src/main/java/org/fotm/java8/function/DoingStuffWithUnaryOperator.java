package org.fotm.java8.function;

import java.util.function.UnaryOperator;

public class DoingStuffWithUnaryOperator {

    private static final UnaryOperator<Integer> square = i -> i * i;

    public static void main(String[] args) {

        System.out.println(square.apply(2));
    }
}
