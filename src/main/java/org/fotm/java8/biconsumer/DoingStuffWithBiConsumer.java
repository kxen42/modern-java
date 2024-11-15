package org.fotm.java8.biconsumer;

import org.fotm.model.Customer;
import org.fotm.model.CustomerGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DoingStuffWithBiConsumer {

    private static final List<Customer> customers = CustomerGenerator.createCustomers();

    private static final Predicate<Customer> moreThanThreeOrders = c -> c.getOrders().length > 3;

    private static final BiConsumer<String, Integer[]> iCanUseArrays = (n, o) -> System.out.println("name: " + n + ", orders: " + Arrays.stream(o)
                                                                                                                                        .map(Object::toString)
                                                                                                                                        .collect(Collectors.joining(", ")));

    private static final BiConsumer<Integer, Integer> multiply = (x, y) -> System.out.println("x * y = " + (x * y));
    private static final BiConsumer<Integer, Integer> add = (x, y) -> System.out.println("x + y = " + (x + y));

    public static void main(String[] args) {
        biConsumerAndArrays();
        chain();
        custmersWithMoreThanThreeOrders();
        mixPredicateDeclarationSyntax();
    }

    public static void biConsumerAndArrays() {
        System.out.println(" ----- biConsumerAndArrays");
        customers.forEach(c -> iCanUseArrays.accept(c.getName(), c.getOrders()));
    }

    public static void chain() {
        System.out.println(" ----- chain");
        multiply.andThen(add)
                .accept(42, 15);

        BiConsumer<Integer, Integer> integerIntegerBiConsumer = multiply.andThen(add);
        integerIntegerBiConsumer.accept(42, 10);

    }

    public static void custmersWithMoreThanThreeOrders() {
        System.out.println(" ----- custmersWithMoreThanThreeOrders");
        customers.stream().filter(moreThanThreeOrders).forEach(c -> iCanUseArrays.accept(c.getName(), c.getOrders()));
    }


    public static void mixPredicateDeclarationSyntax() {
        System.out.println(" ----- mixPredicateDeclarationSyntax");
        customers.stream()
                 .filter(moreThanThreeOrders.and(c -> c.getName().contains("Flintstone")))
                 .forEach(c -> iCanUseArrays.accept(c.getName(), c.getOrders()));
    }
}
