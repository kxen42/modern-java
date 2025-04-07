package org.fotm.java17.patternmatching;

public class GuardedPatternMatching {

    public static void main(String[] args) {
        doGuardedV1();
        doGuardedV2();
    }

    public static void doGuardedV1() {
        System.out.println("----- doGuardedV1");
        guardedV1(null); // throws NPE w/o null case
        guardedV1(-1);
        guardedV1(0);
        guardedV1(1);
        guardedV1(2);
    }

    public static void doGuardedV2() {
        System.out.println("----- doGuardedV2");
        System.out.println(guardedV2(null));
        System.out.println(guardedV2('a'));
        System.out.println(guardedV2('7'));
        System.out.println(guardedV2('8'));
        System.out.println(guardedV2(' '));
        System.out.println(guardedV2('Z'));
    }

    public static void guardedV1(Integer integer) {
        switch (integer) {
            case -1, 1 -> System.out.println("constants case " + integer);
            case Integer i when i > 0 -> System.out.println("guarded case " + i);
            case Integer i -> System.out.println("all other Integer " + i);
            case null -> System.out.println("(NIL)");
        }
    }

    public static String guardedV2(Character o) {
        return switch (o) {
            case Character c
                when c == '7' -> "seven";
            case Character c
                when c == '8' -> "eight";
            case Character c -> {
                if (c < '7') {
                    yield "< 7";
                } else if (c > '9' && c < 'Z') {
                    yield ("c > 9 && c < 26");
                } else {
                    yield "else";
                }
            }
            case null -> "(NIL)";
        };
    }
}
