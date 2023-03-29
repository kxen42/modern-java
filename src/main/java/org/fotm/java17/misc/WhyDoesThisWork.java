package org.fotm.java17.misc;

/**
 * Why do these uses of {@code Tennis.hit()} work.
 * It must have something to do with the fact that it is in the same file.
 *
 * Answer from instructor
 *     It works because the method is {@code static}. If you make the method {@code hit} an
 *     instance method, it fails to compile. Being private is no issue as we
 *     are using the {@code Tennis} class to access the method.
 */
public class WhyDoesThisWork {
    public interface Tennis {
        private static void hit(String stroke) {
            System.out.println("Hitting a " + stroke);
        }
    }

    public static class TennisImpl implements Tennis {
        public void callHit() {
            Tennis.hit("why does this work?");
        }
    }

    /*
    Why can this print this:
    Hitting a why does this work?
    Hitting a why does this work?
     */
    public static void main(String[] args) {

        // I did not expect this
        Tennis.hit("why does this work?");  // public static method Why can I do this?
        new TennisImpl().callHit();
    }
}


