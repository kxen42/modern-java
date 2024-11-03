package org.fotm.java17.methodreference;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * by Sean Kennedy, from Udemy course
 */
public class MainUnboundMethodReference {
    public static void main(String[] args) {
        unboundMethodReferences();      // unbound
    }

    public static void unboundMethodReferences() {

        Function<String, String> upperLambda = s -> s.toUpperCase();
        // not using toUpperCase on a local variable, using it on a class instance method
        Function<String, String> upperMethodReference = String::toUpperCase;

        // The function is unbound, so you need to specify which instance to call it on
        System.out.println(upperLambda.apply("sean"));   // SEAN
        System.out.println(upperMethodReference.apply("sean"));  // SEAN


        BiFunction<String, String, String> concatLambda = (s1, s2) -> s1.concat(s2);
        BiFunction<String, String, String> concatMethodReference = String::concat;

        System.out.println(concatLambda.apply("Sean ", "Kennedy")); // Sean Kennedy

        // 1st parameter is used for executing the instance method
        // "Sean ".concat("Kennedy")
        System.out.println(concatMethodReference.apply("Sean ", "Kennedy"));// Sean Kennedy
    }
}
