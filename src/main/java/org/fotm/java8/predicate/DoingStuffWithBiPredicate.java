package org.fotm.java8.predicate;

import org.fotm.model.Customer;
import org.fotm.model.CustomerGenerator;
import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

/**
 * Experiments with BiPredicate.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html#toMap(java.util.function.Function,java.util.function.Function,java.util.function.BinaryOperator)">BiPredicate</a>
 */
public class DoingStuffWithBiPredicate {

    private static final List<User> users = UserGenerator.createUsers();
    private static final List<Customer> customers = CustomerGenerator.createCustomers();

    private static final BiPredicate<Integer, String> integerEqualsString = (i, s) -> i.equals(Integer.valueOf(s));
    private static final BiPredicate<User, Customer> isUserAndCustomer = (u, c) -> c.getName()
                                                                                    .contains(u.getFirstName() + " " + u.getLastName());

    private static final BiPredicate<Customer, String> findCustomer = (customer, name) -> name.equals(customer.getName());

    private static final BiPredicate<String, String> equalsUpperCase = (s1, s2) -> s1.equals(s2.toUpperCase());
    private static final BiPredicate<String, String> equalsLowerCase = (s1, s2) -> s1.equals(s2.toLowerCase());

    public static void main(String[] args) {
        compareIntegerAndString();
        compareUserAndCustomer();
        biPredicateChain();
    }

    public static void compareIntegerAndString() {
        System.out.println(" ----- compareIntegerAndString");
        boolean test = integerEqualsString.test(42, "42");
        System.out.println(test);
        System.out.println(integerEqualsString.test(3 % 2, "0"));
    }

    public static void compareUserAndCustomer() {
        System.out.println(" ----- compareUserAndCustomer");
        String name = "Fred Flintstone";

        Optional<Customer> optionalCustomer = customers.stream()
                                                       .filter(c -> findCustomer.test(c, name))
                                                       .findFirst();
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            users.stream()
                 .filter(u -> isUserAndCustomer.test(u, customer))
                 .forEach(System.out::println);
        }
    }

    public static void biPredicateChain() {
        System.out.println(" ----- biPredicateChain");
        System.out.println(
            equalsUpperCase.or(equalsLowerCase)
                           .test("yes", "YES")
        );
        System.out.println(
            equalsUpperCase.or(equalsLowerCase)
                           .test("Yes", "YES")
        );
    }
}
