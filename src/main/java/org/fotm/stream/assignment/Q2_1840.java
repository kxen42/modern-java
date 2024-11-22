package org.fotm.stream.assignment;

import java.util.Arrays;
import java.util.List;

/**
 * Assignment (QID 2.1840)
 * <p>
 * 13. This question demonstrates <em>lazy evaluation</em>. Declare the following {@code List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);}
 * <p>
 * a. stream the {@code List} (note that this is possible because List is a Collection and Collection defines a {@code stream()} method); ensure only <em>distinct</em>
 * (unique) numbers are streamed; check if “any match” 11. You should get true for this.<br>
 * b. stream the {@code List} again (this is necessary because once a stream is closed by a previous terminal operation, you must re-create the stream);
 * check to see if “none match” the expression {@code x % 11 > 0}. Note that the terminal operation noneMatch(Predicate) needs to return false for every element
 * in the stream for {@code noneMatch()} to return true. In other words, “none of them match this....that’s correct, none of them do; return true”. You should
 * get {@code true} here as well.
 */
public class Q2_1840 {
    public static void main(String[] args) {
        a();
        b();
    }

    public static void a() {
        System.out.println(" ----- a");
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);

        // Java only generates the amount of stream you need (lazy evaluation).
        // That means that for anyMatch, it quits processing the stream the first time one is 11.
        boolean b = ls.stream()
                      .distinct()
                      .anyMatch(i -> i == 11);

        System.out.println("did any match 11? " + b);
    }

    public static void b() {
        System.out.println(" ----- b");
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
        boolean b = ls.stream()
                      .noneMatch(x -> x % 11 > 0);

        System.out.println("do anyMatch x % 11 > 0? " + b);

    }
}
