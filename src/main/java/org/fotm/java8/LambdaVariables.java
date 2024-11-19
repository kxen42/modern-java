package org.fotm.java8;

import java.util.function.Consumer;

/**
 * Local variables and instances variables in lambdas.
 * Instance variables don't have any restrictions.
 * Local variables have oodles of restrictions.
 */
public class LambdaVariables {

    private static Integer z = Integer.MIN_VALUE;
    private Integer x = 47;

    public static void main(String[] args) {
        Integer i = 42;

        // Compile error because lambda parameter name can't be same as local variable
        // Consumer<Integer> c1 = (i) -> System.out.println("parameter name restriction");

        // prints 44, masking instance variable
        Consumer<Integer> c2 = (x) -> System.out.println("x: " + x);
        c2.accept(44);

        // Compile error: can't have declare variable in lambda block with same names as local variable
//        Consumer<Integer> c3 = () -> {
//            Integer i = 33;
//            System.out.println("block variable in lambda");
//        };

        // prints 52, masking instance variable
        Consumer<Integer> c4 = (y) -> {
            Integer x = 52;
            System.out.println("X: " + x);
        };
        c4.accept(-2);

//        Compile error: can't reassign value to local variable
//        Consumer<Integer> c5 = (y) -> {
//          i = -3;
//        };

        // prints 47 before, and -4 after. Changing instance variable
        LambdaVariables lv = new LambdaVariables();
        Consumer<Integer> c6 = (y) -> {
            System.out.println("before: " + lv.x);
            lv.x = -4;
            System.out.println("after: " + lv.x);
        };
        c6.accept(Integer.MAX_VALUE);

        // prints 0
        Consumer<Integer> c7 = (z) -> System.out.println("mask static as parameter: " + z);
        c7.accept(0);
        // prints Integer.MIN_VALUE
        System.out.println("main static var: " + z);


        // prints -25
        Consumer<Integer> c8 = (y) -> {
            Integer z = -25;
            System.out.println("mask static in block: " + z);
        };
        c8.accept(1);
        // prints Integer.MIN_VALUE
        System.out.println("main static var: " + z);


        Consumer<Integer> c9 = (y) -> {
            // prints Integer.MIN_VALUE
            System.out.println("static before: " + z);
            z = 23;
            // prints 23
            System.out.println("static after: " + z);
        };
        c9.accept(2);
        // prints 23
        System.out.println("main static var: " + z);
    }
}
