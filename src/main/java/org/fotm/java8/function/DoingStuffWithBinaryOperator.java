package org.fotm.java8.function;

import java.util.function.BinaryOperator;

public class DoingStuffWithBinaryOperator {

    private static final BinaryOperator<Double> tangent = (x, y) -> Math.sin(x) / Math.cos(y);

    public static void main(String[] args) {

        System.out.println("tangent x & y: " + tangent.apply(Math.PI, 2.0));
    }
}
