package org.fotm.parallel;

import java.util.stream.IntStream;

/**
 * When changing state in the elements, you must make them thread safe.
 */
public class ToBeThreadsafeOrNotToBeThreadSafe {

    public static void main(String[] args) {

        SumNotThreadsafe sumNotThreadsafe = new SumNotThreadsafe();
        SumThreadSafe sumThreadSafe = new SumThreadSafe();

        IntStream.rangeClosed(0, 1000)
                 .forEach(sumNotThreadsafe::performSum);
        System.out.println(sumNotThreadsafe.getTotal()); // expect 500500

        IntStream.rangeClosed(0, 1000)
                 .parallel()
                 .forEach(sumNotThreadsafe::performSum);
        System.out.println(sumNotThreadsafe.getTotal()); // different everytime you run this

        IntStream.rangeClosed(0, 1000)
                 .parallel()
                 .forEach(sumThreadSafe::performSum);
        System.out.println(sumThreadSafe.getTotal()); // always 500500
    }

}
