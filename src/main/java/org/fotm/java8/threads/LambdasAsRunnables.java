package org.fotm.java8.threads;

/**
 * {@code FunctionalInterfaces} has one abstract class; therefore, it is a {@Function} and it can be implemented bu a lambda.
 */
public class LambdasAsRunnables {
    public static void main(String[] args) {
        Runnable lambdaExpression = () -> System.out.println("Inside lambdaExpression 1");

        Runnable lambdaBlock = () -> {
            System.out.println("Inside lambdaBlock 2");
            System.out.println("Inside lambdaBlock 3");
        };


        new Thread(lambdaExpression, "lambdaExpression").start();
        new Thread(lambdaBlock, "lambdaBlock").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside anonymous class 4");
            }
        }, "anonymous class").start();

        new Thread(() -> System.out.println("Inside FunctionalInterfaces 5"), "bob").start();
    }
}
