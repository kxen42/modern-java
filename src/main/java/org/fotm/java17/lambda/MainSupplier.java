package org.fotm.java17.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MainSupplier {
    public static void main(String[] args) {
        Supplier<Integer> sup = () ->  42;
        Supplier<String[]> strings = () -> "fred".split("");

        System.out.println(sup.get());

        Arrays.stream(strings.get()).sorted().forEach(System.out::println);

        // what happens when you use Supplier to new something
        Supplier<X> someX =  () -> new X();
        X x = someX.get();
        x.name = "Bob";
        System.out.println(x);

        System.out.println(someX.get());

    }

    static class X {
        String name = "default name";

        @Override
        public String toString() {
            return "X{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
