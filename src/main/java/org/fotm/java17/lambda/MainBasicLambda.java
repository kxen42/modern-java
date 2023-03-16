package org.fotm.java17.lambda;

interface I {
    void m();
}
public class MainBasicLambda {

    public static void main(String[] args) {

        I boo = () -> System.out.println("boo");
        boo.m();
    }
}
