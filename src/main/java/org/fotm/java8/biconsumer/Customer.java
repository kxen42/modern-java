package org.fotm.java8.biconsumer;

public class Customer {

    private final String name;
    private final Integer[] orders;

    public Customer(String name, Integer[] orders) {
        this.name = name;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public Integer[] getOrders() {
        return orders;
    }
}
