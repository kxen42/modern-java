package org.fotm.java17.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This assignment will demonstrate the different types on method references - bound, unbound, static and constructor. As you will see, when defining method references, context is key.
 * When learning method references, I believe it really helps to compare them to their equivalent lambda expressions. Thus, in each exercise, you are asked to code the equivalent lambda expression also.
 * The instructions are in the downloadable PDF resource. The PDF file contains a simple UML class diagram and detailed instructions of what is required. The instructions are also in the Assignment questions - use whichever you prefer.
 * The video explains the solutions. In addition, the solutions Java code is available as a downloadable resource.
 * <p>
 * Questions for this assignment
 * <p>
 * Static method references:
 * <p>
 * a. in staticMR(), declare a List of integers with 1, 2, 7, 4, and 5 as values.
 * b. using a Consumer typed for List<Integer> and the Collections.sort static method, code a lambda that sorts the list passed  in.
 * c. invoke the lambda.
 * d. prove that the sort worked.
 * e. re-initialise the list (so it is unsorted again).
 * f. code the method reference version.
 * i. sort() is overloaded : sort(List) and sort(List, Comparator)
 * ii. how does Java know which version to call?
 * g. invoke the method reference version.
 * h. prove that the sort worked.
 * <p>
 * <p>
 * Bound method references (calling instance methods on a particular object):
 * <p>
 * a. in boundMR(), declare a String variable called name and initialise it to “Mr. Joe Bloggs”.
 * b. using a Predicate typed for String, code a lambda that checks to see if name starts with the prefix passed in.
 * c. invoke the lambda passing in “Mr.” which should return true.
 * d. invoke the lambda passing in “Ms.” which should return false.
 * e. code the method reference version.
 * f. repeat c and d above except using the method reference version.
 * <p>
 * <p>
 * Unbound method references (calling instance methods on a parameter):
 * <p>
 * a. in unboundMR(), code a Predicate lambda typed for String that checks to see if the string passed in is empty.
 * b. invoke the lambda passing in “” (returns true).
 * c. invoke the lambda passing in “xyz” (returns false).
 * d. code the method reference version of the lambda from (a).
 * e. repeat b and c above except using the method reference version.
 * f. code a BiPredicate lambda typed for String and String:
 * i. the lambda takes in two parameters (hence “Bi”)
 * ii. check if the first parameter starts with the second parameter
 * iii. invoke the lambda twice:
 * 1. passing in “Mr. Joe Bloggs” and “Mr.” (returns true)
 * 2. passing in “Mr. Joe Bloggs” and “Ms.” (returns false)
 * g. code the method reference version of the lambda from (f).
 * h. test it as per above in (f.iii)
 * <p>
 * <p>
 * Constructor method references:
 * <p>
 * a. in constructorMR(), code a Supplier typed for List<String> that returns a new ArrayList.
 * b. invoke the lambda to create a new List<String> named list.
 * c. add “Lambda” to the list.
 * d. output the list to show it worked.
 * e. code the method reference version of the lambda:
 * i. re-initialise list by invoking the method reference version.
 * ii. add “Method Reference” to the list.
 * iii. output the list to show it worked.
 * f. next, we want to use the overloaded ArrayList constructor passing in 10 as the initial capacity (note: the default
 * constructor assumes a capacity of 10).
 * i. thus, we need to pass IN something and get back OUT something:
 * 1. IN: 10 OUT: ArrayList
 * ii. we need a Function typed for Integer and List<Integer> for this.
 * iii. code the lambda.
 * iv. re-initialise the list by invoking the lambda passing in 10 as the capacity.
 * v. add “Lambda” to the list.
 * vi. output the list to show it worked.
 * g. code the method reference version.
 * i. note that the method reference version is the exact same as above in e!!
 * ii. this is where context is all important:
 * 1. the first method reference was for a Supplier and Supplier’s functional method is T get() and thus, Java knew
 * to look for the ArrayList constructor that takes in NO argument
 * 2. the first method reference was for a Function and Function’s functional method is R apply(T t) and thus, Java knew
 * to look for the ArrayList constructor that takes in ONE argument.
 */
public class AssignmentForMethodReferences {

    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    /*

    This shows the importance of context when working with method references.

    Context "Consumer<List<Integer>> mrSort = Collections::sort;" means that "Collections.sort(List<T>)" is used.

    Context "Consumer<List<Integer>> mrSort = Collections::sort;" means that
    "Collections.sort(List<T>, Comparator<T>);" is used.

     */
    public static void staticMR() {
        // static method reference => using class static method as the method in the reference
        // Ex. System.out::println

        // use lambda
        List<Integer> list = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> lambdaSort = l -> Collections.sort(l);
        lambdaSort.accept(list);
        System.out.println("list is sorted? " + isSorted(list));

        // Note: this is using Consumer<T>
        // method reference using static method of Collections
        list = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> mrSort = Collections::sort;
        mrSort.accept(list);
        System.out.println("list is sorted? " + isSorted(list));

        // Q. How does mrSort know to use Collections.sort(List) rather than Collections.sort(List, Comparator)?
        // A. When the context is Consumer<T> for Collection::sort it uses the single arg version.
        //    When the context is BiConsumer<T, T> for Collection::sort it would use the two arg version.
    }

    public static void boundMR() {
        // bound method reference => calling instance method on a particular instance
        // Ex. "boo"::hashCode

        // bound because name is a local variable and it's value is known at compile time
        // and startsWith is an instance method
        String name = "Mr. Joe Bloggs";
        Predicate<String> lambdaPrefixCheck = s -> name.startsWith(s);
        System.out.println(name + " starts with " + "'Mr.'? " + lambdaPrefixCheck.test("Mr."));
        System.out.println(name + " starts with " + "'Ms.'? " + lambdaPrefixCheck.test("Ms."));

        Predicate<String> mrPrefixCheck = name::startsWith;
        System.out.println(name + " starts with " + "'Mr.'? " + mrPrefixCheck.test("Mr."));
        System.out.println(name + " starts with " + "'Ms.'? " + mrPrefixCheck.test("Ms."));

    }

    public static void unboundMR() {
        // calling instance methods on a parameter
        Predicate<String> lambda = s -> s.isEmpty();
        System.out.println(lambda.test(""));
        System.out.println(lambda.test("xyz"));

        // Note: this is using Predicate<T>
        // unbound because there is no local var, and isEmpty is an instance method
        Predicate<String> mr = String::isEmpty;
        System.out.println(mr.test(""));
        System.out.println(mr.test("xyz"));

        BiPredicate<String, String> biPredicateLambda = (s1, s2) -> s1.startsWith(s2);
        System.out.println(biPredicateLambda.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(biPredicateLambda.test("Mr. Joe Bloggs", "Ms."));

        // Note: this is using BiPredicate<T, U>
        // I did not know you could do this
        BiPredicate<String, String> biPredicateMR = String::startsWith;
        System.out.println(biPredicateMR.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(biPredicateMR.test("Mr. Joe Bloggs", "Ms."));
    }

    /*
    This shows the importance of context when working with method references.

    The Method Reference context "Supplier<List<String>> newArrayListMethodReference = ArrayList::new;"
    means that "new ArrayList<>();" will be used.

    The Method Reference context "Function<Integer, List<String>> mr = ArrayList::new;"
    means that "new ArrayList<>(int);" will be uses.

     */
    public static void constructorMR() {
        Supplier<List<String>> newArrayListSupplier = () -> new ArrayList<>();
        List<String> list = newArrayListSupplier.get();
        list.add("Lambda");
        list.forEach(System.out::println);

        // Note: this is using Supplier<T>
        Supplier<List<String>> newArrayListMethodReference = ArrayList::new;
        list = newArrayListMethodReference.get();
        list.add("Method Reference");
        list.forEach(System.out::println);


        Function<Integer, List<String>> fx = i -> new ArrayList<>(i);
        list = fx.apply(10);
        list.add("Lambda");
        list.forEach(System.out::println);

        // Note: this is using Function<T, R>
        Function<Integer, List<String>> mr = ArrayList::new;
        list = mr.apply(10);
        list.add("Method Reference");
        list.forEach(System.out::println);

    }

    public static boolean isSorted(List<Integer> list) {
        if (list.isEmpty() || list.size() == 1) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 2 && list.get(i) > list.get(i + i)) {
                return false;
            }
        }
        return true;
    }
}
