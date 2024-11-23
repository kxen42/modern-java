package org.fotm.stream.assignment;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Assignment (QID 2.1841)
 * <p>
 * 14. Examine the following code. Note that an AtomicInteger is a version of Integer that is safe to use in concurrent (multi-threaded) environments. The method incrementAndGet() is similar to ++ai
 * <p>
 * a) Why is the value of ai at the end 0 (and not 4)?
 */
public class Q2_1841 {
    public static void main(String[] args) {
        a();
        b();
        fixB();
    }

    public static void a() {
        System.out.println(" ----- a");
        // result is 4 because even in parallel they share the same AtomicInteger
        // and there are 4 items in the stream before the Predicate is checked.
        AtomicInteger ai = new AtomicInteger(); // initial value of 0
        Stream.of(11, 11, 22, 33)
              .parallel()
              .filter(n -> {
                  ai.incrementAndGet();
                  return n % 2 == 0;
              })
              .forEach(System.out::println);//22
        System.out.println(ai);//4
    }

    public static void b() {
        System.out.println(" ----- b");
        AtomicInteger ai = new AtomicInteger(); // initial value of 0
        Stream<Integer> stream = Stream.of(11, 11, 22, 33)
                                       .parallel();
        stream.filter(e -> {
            ai.incrementAndGet();
            return e % 2 == 0;
        });

        try {
            stream.forEach(System.out::println);// java.lang.IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Expect:");
            System.out.println("Exception in thread \"main\" java.lang.IllegalStateException: stream has already been operated upon or closed");
        }
        System.out.println(ai);

    }

    public static void fixB() {
        System.out.println(" ----- fixB");
        AtomicInteger ai = new AtomicInteger(); // initial value of 0
        Stream<Integer> stream = Stream.of(11, 11, 22, 33)
                                       .parallel();

        Stream<Integer> stream2 = stream.filter(e -> {
            ai.incrementAndGet();
            return e % 2 == 0;
        });

        try {
            stream2.forEach(System.out::println);// java.lang.IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("did not expect this");
            throw e;
        }
        System.out.println(ai);

    }
}
