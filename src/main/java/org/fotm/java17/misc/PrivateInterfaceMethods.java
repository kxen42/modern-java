package org.fotm.java17.misc;

/**
 * Look what they've done to interfaces.
 * We still have our favorites: constant variables, abstract methods.
 *
 * Note: Shame upon those that only use an interface to define constants.
 * Use a static final class with a private constructor that throws an exception instead for the kitchen sink
 * constant holders.
 *
 * Now we've got:
 * <ul>
 *     <li>default methods</li>
 *     <li>static methods</li>
 *     <li>private methods</li>
 *     <li>private static methods</li>
 * </ul>
 *
 * The {@code Tennis} interface is mostly from Sean Kennedy.
 * The McEnroe stuff is all mine.
 */
public class PrivateInterfaceMethods {

    public interface LookWhatICanDo {
         int nothingNew = 42;
         void beenThereDoneThat();

         // default methods are always public
         default String defaultMethod(String str) {
             return "the defaultMethod says " + str;
         }
    }

    public interface Tennis {
        private static void hit(String stroke){
            System.out.println("Hitting a "+stroke);
        }
        private void smash(){ hit("smash"); }
        default void forehand(){ hit("forehand"); }
        static void backhand(){
            hit("backhand");
        }

        default void mcEnroe() {
            smash();
            yellAtRef();
            throwRacket();
        }
        private void yellAtRef() {
            System.out.println("Are you blind!");
        }

        private void throwRacket() {
            System.out.println("Watch me throw my racket on the ground!");
        }
    }

    public static class TestInterfaces implements LookWhatICanDo, Tennis {

        @Override
        public void beenThereDoneThat() {
            System.out.println("old fashioned interface");
        }
    }

    public static void main(String[] args) {
        TestInterfaces ti = new TestInterfaces();

        ti.mcEnroe(); // boiler plate

        ti.forehand(); // default methods

        // I did not expect this
        Tennis.hit("fuzzy ball");  // public static method Why can I do this?

        Tennis.backhand(); // public static methods

//        ti.smash(); no can do with private method
    }
}
