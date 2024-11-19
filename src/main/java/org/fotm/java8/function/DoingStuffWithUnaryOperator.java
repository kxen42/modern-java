package org.fotm.java8.function;

import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;

public class DoingStuffWithUnaryOperator {

    private static final UnaryOperator<Integer> square = i -> i * i;

    private static Double squareMethod(Double d) {
        return d * d;
    }


    public static void main(String[] args) {

        System.out.println(" ----- UnaryOperator as lambda expression");
        System.out.println(square.apply(2));

        System.out.println(" ----- UnaryOerator as Method Reference");
        DoubleStream.of(9, 11, 12)
                    .map(DoingStuffWithUnaryOperator::squareMethod)
                    .forEach(System.out::println);
    }
}
