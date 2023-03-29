package org.fotm.java17.misc;

/**
 * Why do these uses of {@code Tennis.hit()} work.
 * It must have something to do with the fact that it is in the same file.
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


