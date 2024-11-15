package org.fotm.model;

import java.util.List;

public class CustomerGenerator {

    public static List<Customer> createCustomers() {
        return List.of(
            new Customer("Fred Flintstone", new Integer[]{14, 42, 54}),
            new Customer("Wilma Flintstone", new Integer[]{1444, 16, 80, 6}),
            new Customer("Betty Rubble", new Integer[]{100}),
            new Customer("Barney Rubble", new Integer[]{})
        );
    }
}
