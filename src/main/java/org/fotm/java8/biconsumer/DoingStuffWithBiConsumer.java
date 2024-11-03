package org.fotm.java8.biconsumer;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class DoingStuffWithBiConsumer {

    public static void main(String[] args) {


        Customer fred = new Customer("Fred", new Integer[]{34, 42, 990});

        // Look at that - it can use arrays
        // Using streams inline in a print statement
        BiConsumer<String, Integer[]> customerConsumer = (name, orders) -> System.out.println("name :" + name +
                                                                                                  " orders: " + Arrays.stream(orders)
                                                                                                                      .map(Object::toString)
                                                                                                                      .collect(Collectors.joining(", ")));

    }

}
