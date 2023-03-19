package org.fotm.java17.methodreference;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Catch all
 */
public class MainMiscMethodReference {

    public static void main(String[] args) {
        theImportanceOfContext();
        passMethodReferenceAsArg();
    }

    // How does the compiler know what to do with a method reference?
    // It has to figure it oub based on context.
    // from Sean Kennedy
    static void theImportanceOfContext() {

        // you cannot use var because the type cannot be inferred
//        var supplierMethRef = Person::howMany;

        // How context works with method references
        Supplier<Integer> supplierMethRef = Person::howMany;
        Function<Person, Integer> functionMetRef = Person::howMany;
        BiFunction<Person, Person, Integer> biFunLMR = Person::howMany;

        Integer zeroPeep = supplierMethRef.get(); // 0
        Integer onePeep = functionMetRef.apply(new Person()); // 1
        Integer twoPeep = biFunLMR.apply(new Person(), new Person()); // 2


    }

    // wanted to know what this would do
    static void passMethodReferenceAsArg() {
        usesBiFunction(Person::howMany);
    }
    static void usesBiFunction(BiFunction<Person, Person, Integer> biFunction) {
        System.out.println(biFunction.apply(new Person(), new Person()));
    }
    static class Person {
        public static Integer howMany(Person... people) {
            return people.length;
        }
    }
}
