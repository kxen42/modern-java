package org.fotm.java17.lambda;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * My experiments with lambdas used in recursive Fibonacci algorithms.
 * Also, more adventures with BigIntegers.
 * <p>
 * by Karen Christenson
 */
public class MainLambdaRecursion {

    // You can use an instance property or a class property in a lambda
    public static UnaryOperator<Integer> fibonacciUnaryOperator;
    public static BiFunction<Integer, Map<Integer, Integer>, Integer> memoized;
    public static BiFunction<BigInteger, Map<BigInteger, BigInteger>, BigInteger> memoizedBiggy;

    static {
        fibonacciUnaryOperator = (i) -> {
            if (i <= 2) return 1;
            return fibonacciUnaryOperator.apply(i - 1) + fibonacciUnaryOperator.apply(i - 2);
        };
    }

    static {
        memoized = (i, memo) -> {
            if (memo.keySet()
                    .contains(i)) return memo.get(i);
            memo.put(0, 0);
            memo.put(1, 1);
            if (i <= 2) return 1;
            return memoized.apply(i - 1, memo) + memoized.apply(i - 2, memo);
        };
    }

    static {
        memoizedBiggy = (i, memo) -> {
            memo.put(BigInteger.ZERO, BigInteger.ZERO);
            memo.put(BigInteger.ONE, BigInteger.ONE);
            if (memo.keySet()
                    .contains(i)) return memo.get(i);
            if (i.compareTo(BigInteger.TWO) < 0) return BigInteger.ONE;

            // readable - it doesn't make my eyes sweat
//            BiFunction<BigInteger, Map<BigInteger, BigInteger>, BigInteger> left = (i2, memo2) -> memoizedBiggy.apply(i2.subtract(BigInteger.ONE), memo2);
//            BiFunction<BigInteger, Map<BigInteger, BigInteger>, BigInteger> right = (i3, memo3) -> memoizedBiggy.apply(i3.subtract(BigInteger.TWO), memo3);
//            memo.put(i, left.apply(i, memo).add(right.apply(i, memo)));

            // works not very readable
            memo.put(i, memoizedBiggy.apply(i.subtract(BigInteger.ONE), memo)
                                     .add(memoizedBiggy.apply(i.subtract(BigInteger.TWO), memo)));

            return memo.get(i);
        };
    }

    public UnaryOperator<BigInteger> instanceVarUnaryOperator;

    public MainLambdaRecursion() {
        this.instanceVarUnaryOperator = (i) -> {
            if (i.compareTo(BigInteger.TWO) < 0) return BigInteger.ONE;
            return this.instanceVarUnaryOperator.apply(i.subtract(BigInteger.ONE))
                                                .add(this.instanceVarUnaryOperator.apply(i.subtract(BigInteger.TWO)));
        };
    }

    public static Integer fibonacci(Integer x) {
        if (x <= 2) return 1;
        return fibonacci(x - 1) + fibonacci(x - 2);
    }

    public static BigInteger memoizedFibonacciBI(BigInteger i, Map<BigInteger, BigInteger> memo) {
        memo.put(BigInteger.ZERO, BigInteger.ZERO);
        memo.put(BigInteger.ONE, BigInteger.ONE);
        if (memo.keySet()
                .contains(i)) return memo.get(i);
        if (i.compareTo(BigInteger.TWO) < 0) return BigInteger.ONE;
        memo.put(i, memoizedFibonacciBI(i.subtract(BigInteger.ONE), memo)
            .add(memoizedFibonacciBI(i.subtract(BigInteger.TWO), memo)));
        return memo.get(i);
    }

    public static void main(String[] args) {
        System.out.println("recursive function " + fibonacci(10));

        System.out.println("recursive static lambda " + fibonacciUnaryOperator.apply(10));

        MainLambdaRecursion mlr = new MainLambdaRecursion();
        System.out.println("recursive instance var lambda " + mlr.instanceVarUnaryOperator.apply(BigInteger.valueOf(3l)));

        System.out.println("memoized recursive static lambda " + memoized.apply(10, new HashMap<>()));

        System.out.println("memoized recursive function using BigInteger " + memoizedFibonacciBI(BigInteger.valueOf(100l), new HashMap<>()));

        System.out.println("memoized recursive lambda using BigInteger " + memoizedBiggy.apply(BigInteger.valueOf(100l), new HashMap<>()));
    }
}
