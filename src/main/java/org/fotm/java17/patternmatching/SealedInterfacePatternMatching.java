package org.fotm.java17.patternmatching;

import org.fotm.model.Animal;
import org.fotm.model.Cat;
import org.fotm.model.Dog;

/**
 * Sealed interface it class implementations. For sealed interface/class, now default case is not needed.
 * <p>
 * You can have a default it could never be reached unless the item is a member of the sealed interface/class that
 * is not matched in the switch.
 * <p>
 *  Interview question: If you don't have a case for null, you will get and NPE, a null is not covered by the default.
 * <p>
 * Just matching on the type of sealed interface instances,
 */
public class SealedInterfacePatternMatching {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.name("Chester");
        Dog dog = new Dog();
        dog.name("Harold");

        // saySomething(null); throws NPE
        saySomething(cat);
        saySomething(dog);

        System.out.printf("|%s| - |%s| - |%s|%n", retrieveName(null), retrieveName(cat), retrieveName(dog));
    }

    public static void saySomething(Animal critter) {
        switch (critter) {
            case Cat c -> c.meow("Watch me shove this off the counter human!");
            default -> System.out.println("Default Critter is " + critter.name()); // won't cover null
        }
    }

    public static String retrieveName(Animal animal) {
        return switch (animal) {
            case null -> "Was null"; // This handles the null and avoids NPE, does not handle default
            case Cat cat -> cat.name();
            case Dog dog -> dog.name();
        };
    }
}
