package org.fotm.java17.misc;

/**
 * Private interface method example from Dr. Sean Kennedy.
 * The mcEnroe parts is added for grins and giggles.
 */
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
