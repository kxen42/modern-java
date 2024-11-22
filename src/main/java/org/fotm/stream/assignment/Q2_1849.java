package org.fotm.stream.assignment;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Assignment (QID 2.1810)
 * <p>
 * 10. A question about {@code Optional}. Let us look at this in parts:
 * <p>
 * a. Declare an  {@code Optional}, typed for  {@code Double}, named ‘price’ using the  {@code Optional.ofNullable(20.0)}.
 * Output the  {@code Optional} value for ‘price’ 3 times: using {@code ifPresent(Consumer)}, {@code orElse(T)} and
 * {@code orElseGet(Supplier)}.
 * <p>
 * b. declare a new  {@code Optional}, typed for  {@code Double}, named ‘price2’ (or comment out (a) and re-use
 * ‘price’). Use  {@code Optional.ofNullable} again but this time, pass in {@code null}.<br>
 * i. Output ‘price2’ in a normal {@code System.out.println()}.<br>
 * ii. check to see if price2 {@code isEmpty()} and if so output “empty”.<br>
 * iii. do (ii) again except this time use the more functional {@code ifPresent(Consumer)}method.<br>
 * iv. initialise a  {@code Double} x to the return of {@code price2.orElse(44.0)}. Output and observe the
 * value of x.
 * <p>
 * c. declare a new  {@code Optional}, typed for  {@code Double}, named ‘price3’ (or comment out (b) and re-use
 * ‘price’). Use  {@code Optional.ofNullable} passing in {@code nul}.<br>
 * i. initialise a  {@code Double} z to the return of {@code price3.orElseThrow(() -> new
 * RuntimeException(“Bad Code”)}. Output and observe the value of z.
 */
public class Q2_1849 {
    public static void main(String[] args) {
        a();
        b();
        c();
        hisSolution();
        validOptionalOf();
    }

    /*
     * a. Declare an  {@code Optional}, typed for  {@code Double}, named ‘price’ using the  {@code Optional.ofNullable(20.0)}.
     * Output the  {@code Optional} value for ‘price’ 3 times: using {@code ifPresent(Consumer)}, {@code orElse(T)} and
     * {@code orElseGet(Supplier)}.*/
    private static void a() {
        System.out.println(" ----- a");
        Optional<Double> price = Optional.ofNullable(20.0);
        price.ifPresent(p -> System.out.println("ifPresent(Consumer): " + p));
        Double orElse = price.orElse(42.0);
        System.out.println("orElse(T): " + orElse);
        Double orElseGet = price.orElseGet(Math::random);
        System.out.println("orElseGet(Supplier: " + orElseGet);
    }

    /**
     * b. declare a new  {@code Optional}, typed for  {@code Double}, named ‘price2’ (or comment out (a) and re-use
     * ‘price’). Use  {@code Optional.ofNullable} again but this time, pass in {@code null}.<br>
     * i. Output ‘price2’ in a normal {@code System.out.println()}.<br>
     * ii. check to see if price2 {@code isEmpty()} and if so output “empty”.<br>
     * iii. do (ii) again except this time use the more functional {@code ifPresent(Consumer)}method.<br>
     * iv. initialise a  {@code Double} x to the return of {@code price2.orElse(44.0)}. Output and observe the
     * value of x.
     */
    public static void b() {
        System.out.println(" ----- b");
        Optional<Double> price = Optional.ofNullable(null);
        System.out.println("price: " + price);
        System.out.println("price isEmpty: " + (price.isEmpty() ? "empty" : price));
        price.ifPresent(p -> System.out.println("price ifPresent(Consumer): " + p));
        Double x = price.orElse(44.0);
        System.out.println("x orElse: " + x);
    }

    /*
     * c. declare a new  {@code Optional}, typed for  {@code Double}, named ‘price3’ (or comment out (b) and re-use
     * ‘price’). Use  {@code Optional.ofNullable} passing in {@code null)}.<br>
     * i. initialise a  {@code Double} z to the return of {@code price3.orElseThrow(() -> new
     * RuntimeException(“Bad Code”)}. Output and observe the value of z.
     */
    public static void c() {
        System.out.println(" ----- c");
        Optional<Double> price = Optional.ofNullable(null);
        try {
            Double z = price.orElseThrow(() -> new RuntimeException("Bad Code")); // must use "() ->" to make it a Supplier
        } catch (RuntimeException e) {
            System.out.println("Threw expected exception from the Supplier of orElseThrow");
        }
    }

    /**
     * {@code ofNullable} explained:
     * {@code Optional o = Optional.ofNullable(value)}
     * is the same as:
     * {@code Optional o = (value == null) ? Optional.empty() : Optional.of(value)}
     */
    public static void hisSolution() {
        System.out.println(" ----- hisSolution");

        Supplier<Void> yt = () -> {
            // Q asked on YT:
            System.out.println("------------------------- Q asked on YT");
            Optional<Double> price = Optional.ofNullable(null); // null passed in
            System.out.println(price); // Optional.empty
            Double x = price.orElse(null);
            System.out.println(x); // null
            Double x2 = price.orElseGet(() -> null);// Supplier
            System.out.println(x2); // null

            try {
                price.orElseThrow();
            } catch (Exception e) {
                System.out.println("got expected NPE for orElseThrow w/o Supplier");
            }
            return null;
        };

        yt.get();

        Supplier<Void> a = () -> {
            System.out.println(" ----- his solution a");
            Optional<Double> price = Optional.ofNullable(20.0);
            price.ifPresent(System.out::println);// 20.0
            Double x = price.orElse(22.0);
            System.out.println(x);// 20.0
            Double x2 = price.orElseGet(() -> 33.0);// Supplier
            System.out.println(x);// 20.0
            return null;
        };

        a.get();

        Supplier<Void> b = () -> {
            System.out.println(" ----- his solution b");
            Optional<Double> price = Optional.ofNullable(null); // null passed in
            System.out.println(price); // Optional.empty
            if (price.isEmpty()) {
                System.out.println("empty");// "empty"
            }
            price.ifPresent(System.out::println);// nothing
            Double x = price.orElse(44.0);
            System.out.println(x);// 44.0
            return null;
        };

        b.get();

        Supplier<Void> c = () -> {
            System.out.println(" ----- his solution c");
            Optional<Double> price = null; //  java.lang.NullPointerException
            try {
                price = Optional.of(null);
            } catch (Exception e) {
                System.out.println("Option.of(null) throws NPE");
            }

            try {
                price = Optional.ofNullable(null);
//             Note: no "throw" in Supplier in next statement
                Double z =
                    price.orElseThrow(() -> new RuntimeException("Bad Code"));//java.lang.RuntimeException: Bad Code
                System.out.println(z);
            } catch (RuntimeException e) {
                System.out.println("Threw expected exception from orElseThrow(Supplier)");
            }
            return null;
        };

        c.get();
    }

    /**
     * {@code Optional.of(T)} takes only one arg; there is no varargs version.
     */
    public static void validOptionalOf() {
        System.out.println(" ----- validOptionalOf");
        System.out.println("value: " + Optional.of(42.0));
    }
}
