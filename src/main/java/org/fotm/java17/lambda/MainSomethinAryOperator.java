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

        String str = buildString("a", "b", "=",
                s -> " " + s + " ",
                (s1, s2) -> s1 + " + " + s2);

        System.out.println(str);

    }

    static String buildString(String s1, String s2, String s3, UnaryOperator<String> uni, BinaryOperator<String> bio) {
        String result = uni.apply(s3);
        result += bio.apply(s1, s2);

        return result;
    }
}
