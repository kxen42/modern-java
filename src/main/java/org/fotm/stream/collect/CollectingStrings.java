package org.fotm.stream.collect;

import java.util.stream.Stream;

public class CollectingStrings {
    public static void main(String[] args) {
        usingReduce();
        usingCollect();
    }

    public static void usingReduce() {
        System.out.println(" ----- usingReduce");
        String reduce = Stream.of("a", "b", "c")
                              .parallel()
                              .reduce("",
                                      (n, s) -> n + s,
                                      (n1, n2) -> n1 + n2);
        System.out.println(reduce);

    }

    /**
     * TODO: See how usingReduce this compare for performance in Java 17+
     * This is not guaranteed to return 'abc', it did when running with the debugger.
     */
    public static void usingCollect() {
        System.out.println(" ----- usingCollect");
        StringBuilder collect = Stream.of("a", "b", "c")
                                      .parallel()
                                      .collect(StringBuilder::new,
                                               StringBuilder::append,
                                               StringBuilder::append);
        System.out.println(collect);

    }
}
