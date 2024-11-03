package org.fotm.java17.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * My sorting solutions use the
 * The Comparator<T>
 * comparing(Function<? super T,? extends U> keyExtractor, Comparator<? super U> keyComparator)
 * Accepts a function that extracts a sort key from a type T, and returns a Comparator<T> that compares by that sort key using the specified Comparator.
 * This keyExtractor doesn't need to return something that is already Comparable
 * <p>
 * The more succinct solution is to use
 * comparing(Function<? super T,? extends U> keyExtractor)
 * Accepts a function that extracts a Comparable sort key from a type T, and returns a Comparator<T> that compares by that sort key.
 * <p>
 * I missed that the keyExtractor only needs to be a function that returns a comparable value.
 * The Person.getName() and Person.getHeight do just that. Doh!
 */
public class BasicLambdaAssignmentMyWork {

    public static void main(String[] args) {
        BasicLambdaAssignmentMyWork assignment1 = new BasicLambdaAssignmentMyWork();

        assignment1.consume();
        assignment1.supplier();
        assignment1.predicate();
        assignment1.function();

        List<Person> personList = getPeople();
        sortAge(personList);
        sortName(personList);
        sortHeight(personList);

    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }

    static void sortName(List<Person> peeps) {
        Comparator<String> comparator = String::compareTo;

        peeps.sort(Comparator.comparing(Person::getName, comparator));
        peeps.forEach(System.out::println);
    }

    static void sortAge(List<Person> peeps) {
        Comparator<Integer> comparator = Integer::compareTo;

        peeps.sort(Comparator.comparing(Person::getAge, comparator));
        peeps.forEach(System.out::println);
    }

    static void sortHeight(List<Person> peeps) {
        Comparator<Double> comparator = Double::compareTo;

        peeps.sort(Comparator.comparing(Person::getHeight, comparator));
        peeps.forEach(System.out::println);
    }

    private void consume() {
        Printable<String> printableL = s -> System.out.println(s);
        printableL.print("Printable lambda");

        Printable<String> printableMR = System.out::println;
        printableMR.print("Printable lambda");
    }

    private void supplier() {
        Retrievable<Integer> retrievable = () -> 77;
        retrievable.retrieve();

        Supplier<Integer> supplier = () -> 77;
        supplier.get();
    }

    void predicate() {
        Evaluate<Integer> e = i -> i < 0;
        e.isNegative(-1);
        e.isNegative(1);

        Predicate<Integer> predicate = i -> i < 0;
        predicate.test(-1);
        predicate.test(1);

        BasicLambdaAssignmentMyWork assignment = new BasicLambdaAssignmentMyWork();
        assignment.check(4, x -> x % 2 == 0);
        assignment.check(7, x -> x % 2 == 0);
        Predicate<String> isMr = s -> s.startsWith("Mr.");
        assignment.check("Mr. Joe Bloggs", isMr);
        assignment.check("Ms. Annie Bloggs", isMr);

        Predicate<Person> personPredicate = person -> person.getAge() >= 18;
        Person mike = new Person("Mike", 33, 1.8);
        Person ann = new Person("Ann", 13, 1.4);
        assignment.check(mike, personPredicate);
        assignment.check(ann, personPredicate);
    }

    private <T> Boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }

    private void function() {
        Functionable<Integer, String> functionable = i -> "Number is: " + i;
        functionable.applyThis(25);

        Function<Integer, String> function = i -> "Number is: " + i;
        function.apply(25);
    }

    interface Functionable<T, R> {
        R applyThis(T t);
    }

    interface Evaluate<T> {
        Boolean isNegative(T t);
    }

    interface Retrievable<T> {
        T retrieve();
    }

    interface Printable<T> {
        void print(T t);
    }

    static class Person {
        private Integer age;
        private String name;
        private Double height;

        public Person(String name, Integer age, Double height) {
            this.age = age;
            this.name = name;
            this.height = height;
        }

        public Integer getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public Double getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return "Person{" + "age=" + age + ", name=" + name + ", height=" + height + '}';
        }

    }
}
