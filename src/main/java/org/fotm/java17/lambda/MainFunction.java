package org.fotm.java17.lambda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MainFunction {

    public static void main(String[] args) {

        // identity() is a static method
        Function<Object, Object> identity = Function.identity();
        System.out.println(identity.apply(42));

        Function<Integer, Integer> fx = x -> x * 2;
        System.out.println(fx.apply(128));

        // compose() adds something that runs before the original Function
        Function<Integer, Integer> compopse = fx.compose(x -> x / 3);
        System.out.println(compopse.apply(6));

        Function<Integer, Integer> andThen = compopse.andThen(x -> x + 33);
        System.out.println(andThen.apply(6));

        // Function<T,R> can be different types
        Function<String, Integer> toInt = s -> s.length();
        Function<Integer, String> toString = x -> "oohs and aahs  r"  + x;

        System.out.println(toInt.apply("blah"));
        System.out.println(toString.apply(4096));

        BiFunction<Integer, Integer, String> coordinates = (a, b) -> String.format("(%d, %d)", a, b);
        System.out.println(coordinates.apply(2, 5));

        BiFunction<BigDecimal, BigDecimal, BigDecimal> div = (bd1, bd2) -> bd1.divide(bd2, RoundingMode.CEILING);
        BigDecimal first = BigDecimal.valueOf(22.0);
        BigDecimal second = BigDecimal.valueOf(7.0);
        System.out.println(div.apply(first, second));
    }
}
