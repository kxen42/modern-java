package org.fotm.java17.misc;

/**
 * Look what they've done to interfaces.
 * We still have our favorites: constant variables, abstract methods.
 * <p>
 * Note: Shame upon those that only use an interface to define constants.
 * Use a static final class with a private constructor that throws an exception instead for the kitchen sink
 * constant holders.
 * <p>
 * Now we've got:
 * <ul>
 *     <li>default methods</li>
 *     <li>static methods</li>
 *     <li>private methods</li>
 *     <li>private static methods</li>
 * </ul>
 * <p>
 * The {@code NestedInterfaceInClass} interface is mostly from Sean Kennedy.
 * The McEnroe stuff is all mine.
 */
public class PrivateInterfaceMethods implements NewInterfaceFeatures {

    public static void main(String[] args) {
        PrivateInterfaceMethods instance = new PrivateInterfaceMethods();

        NestedInterfaceInClassImpl tennisImpl = new NestedInterfaceInClassImpl();
        tennisImpl.mcEnroe(); // boiler plate
        tennisImpl.forehand(); // default methods
        NestedInterfaceInClass.backhand(); // public static method
        tennisImpl.defaultMethod(" NewInterfaceFeatures default method");
//        tennisImpl.smash(); <---- no can do with private method

        // I did not expect this; NestedInterfaceInClass is a nested interface
        NestedInterfaceInClass.hit(" {fuzzy ball Why does this work?} ");  // <---- private static method Why can I do this?

        NewInterfaceFeatures.NestedInterfaceInInterface.publicStaticMethod();
    }


    // Nested interface, static is implied so it is redundant here
    // Nested interface must be public and static
    public interface NestedInterfaceInClass {
        int nothingNew = 42;
        // static int CONSTANT = 5; <---- static is redundant here
        // private int i = 4;       <---- still forbidden

        private static void hit(String stroke) {
            System.out.println("Hitting a " + stroke + " (hit is private static) ");
        }

        static void backhand() {
            hit("backhand (backhand is public static) ");
        }

        void beenThereDoneThat();

        // default methods are always public
        default String defaultMethod(String str) {
            return " the public defaultMethod says " + str;
        }

        private void smash() {hit("smash (smash is private) ");}

        default void forehand() {hit("forehand (forehand is public default) ");}

        default void mcEnroe() {
            System.out.println(" (mcEnroe is public default) ");
            smash();
            yellAtRef();
            throwRacket();
        }

        private void yellAtRef() {
            System.out.println("Are you blind! (yellAtRef is private) ");
        }

        private void throwRacket() {
            System.out.println("Watch me throw my racket on the ground! (throwRacket is private) ");
        }
    }

}
