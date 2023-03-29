package org.fotm.java17.misc;

/**
 * Private interface method example from Dr. Sean Kennedy.
 */
public class SportTest implements Tennis {


    public static void main(String[] args) {
        new SportTest().forehand(); // Hitting a forehand
        Tennis.backhand();          // Hitting a backhand
// not allowed       new SportTest().hit();
// not allowed      new SportTest().smash();
        System.out.println("--------------------------------------------------");
        new SportTest().mcEnroe();
    }
}

