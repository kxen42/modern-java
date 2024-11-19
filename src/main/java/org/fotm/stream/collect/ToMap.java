package org.fotm.stream.collect;

import org.fotm.model.Customer;
import org.fotm.model.CustomerGenerator;
import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * {@code Collectors.toMap} experiments.
 */
public class ToMap {

    private static final List<User> users = UserGenerator.createUsers();
    private static final List<Customer> customers = CustomerGenerator.createCustomers();

    public static void main(String[] args) {
        twoArgToMap();
        threeArgToMap();
        fourArgToMap();
        keyOnLengthOfString();
        keyOnStreamElement();
        usingFunctionIdentity();
        mitigatingDuplicateKeys();
        countDuplicateLastNames();
        filterAndToMap();
    }

    /**
     * Confident there are now duplicate keys.
     */
    public static void twoArgToMap() {
        System.out.println(" ----- twoArgToMap");

        // map user to ID
        Map<UUID, User> collect = users.stream()
                                       .collect(toMap(
                                           User::getId,
                                           Function.identity()
                                       ));

        System.out.println(collect);
    }

    /**
     * Handle duplicate keys.
     */
    public static void threeArgToMap() {
        System.out.println(" ----- threeArgToMap");

        Map<String, String> collect = users.stream()
                                           .collect(toMap(
                                               User::getLastName,
                                               User::getFirstName,
                                               (u1, u2) -> String.join(", ", u1, u2))
                                           );

        System.out.println(collect);
    }

    /**
     * Handle duplicate keys.
     * Specify Map type.
     */
    public static void fourArgToMap() {
        System.out.println(" ----- fourArgToMap");

        TreeMap<String, String> collect = users.stream()
                                               .collect(toMap(
                                                   User::getLastName,
                                                   User::getFirstName,
                                                   (u1, u2) -> String.join(", ", u1, u2),
                                                   TreeMap::new)
                                               );

        System.out.println(collect);
    }

    public static void keyOnLengthOfString() {
        // use string length as key, string as value
        // have keys sorted
        System.out.println(" ----- keyOnLengthOfString");


        TreeMap<Integer, String> collect = Stream.of("a", "abc", "def", "h", "uvwxyz")
                                                 .collect(toMap(
                                                     String::length,
                                                     Function.identity(),
                                                     (s1, s2) -> String.join("|", s1, s2),
                                                     TreeMap::new
                                                 ));
        System.out.println(collect);
    }

    public static void keyOnStreamElement() {
        System.out.println(" ----- keyOnStreamElement");

        Map<String, Integer> collect = Stream.of("cake", "cookie", "pie", "cake")
                                             .collect(toMap(
                                                 Function.identity(),
                                                 String::length,
                                                 Integer::sum,
                                                 TreeMap::new
                                             ));

        // {cake=8, cookie=6, pie=3}
        System.out.println(collect);
    }


    public static void usingFunctionIdentity() {
        System.out.println(" ----- usingFunctionIdentity");
        List<String> strings = List.of("Fred", "Wilma", "Barney", "Betty");
        Map<String, Integer> toMap = strings.stream()
                                            .collect(toMap(Function.identity(), String::length));

        /*
        toMap type: java.util.HashMap
        Barney:6
        Wilma:5
        Betty:5
        Fred:4
         */
        System.out.println("toMap type: " + toMap.getClass()
                                                 .getCanonicalName());
        toMap.forEach((x, y) -> System.out.println(x + ":" + y));
    }

    /**
     * Using the {@code toMap} that uses a function to determine how to handle duplicate keys.
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html#toMap(java.util.function.Function,java.util.function.Function,java.util.function.BinaryOperator)">toMap for duplicate keys</a>
     */

    public static void mitigatingDuplicateKeys() {
        System.out.println(" ----- mitigatingDuplicateKeys");
        List<String> strings = List.of("Fred", "Fred", "Fred", "Wilma", "Wilma", "Betty");

        Map<String, Integer> collect = strings.stream()
                                              .collect(toMap(Function.identity(), String::length,
                                                             (item, duplicate) -> item));
        // {Wilma=5, Betty=5, Fred=4}
        System.out.println(collect);
    }

    public static void countDuplicateLastNames() {
        System.out.println(" ----- countDuplicateLastNames");
        Stream<String> lastNames = UserGenerator.createUsers()
                                                .stream()
                                                .map(User::getLastName);

        Map<String, Integer> collect = lastNames.collect(toMap(Function.identity(), // key
                                                               value -> 1, // value
                                                               // do something with the value for duplicate keys
                                                               Integer::sum)); // merge function
        System.out.println(collect);
    }

    public static void filterAndToMap() {
        System.out.println(" ----- filterAndToMap");
        Map<String, List<Integer>> collect = customers.stream()
                                                      .filter(c -> Arrays.asList(c.getOrders())
                                                                         .contains(14))
                                                      .collect(toMap(Customer::getName,
                                                                     customer -> Arrays.asList(customer.getOrders()
                                                                     )));
        collect.forEach((k, v) -> System.out.println("key: " + k + ", v: " + v));
    }

}
