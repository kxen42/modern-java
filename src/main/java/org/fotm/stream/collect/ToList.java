package org.fotm.stream.collect;

import org.fotm.model.Customer;
import org.fotm.model.CustomerGenerator;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@code Collectors.toList()} experiments.
 * <b>Note:</b> since Java 16 this can be done using the {@code Stream.toList()} method.
 */
public class ToList {

    private static final List<Customer> customers = CustomerGenerator.createCustomers();

    public static void main(String[] args) {
        getDistinctOrdersForAllCustomers();
    }


    public static void getDistinctOrdersForAllCustomers() {
        System.out.println(" ----- getDistinctOrdersForAllCustomers");
        List<Integer> collect = customers.stream()
                                         .flatMap((c) -> Arrays.stream(c.getOrders()))
                                         .distinct()
                                         .toList();
    }
}
