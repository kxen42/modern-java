package org.fotm.java17.lambda;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class MainSomethinAryOperator {

    public static void main(String[] args) {
        UnaryOperator<Double> sqr = x -> x * x;
        System.out.println(sqr.apply(3.0));

        UnaryOperator<Double> fx = x -> x - 0.5;
        var withCompose = sqr.compose(fx);
        System.out.println(withCompose.apply(3.0));

        UnaryOperator<Double> gy = y -> y * -1.0;
        var withAndThen = sqr.andThen(gy);
        System.out.println(withAndThen.apply(3.0));

        BinaryOperator<Double> biOperator = (x, y) -> x * y;
        System.out.println(biOperator.apply(4.0, 5.25));

    }
}
