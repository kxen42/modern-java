package org.fotm.java17.sealed;

public class SealedClasses {

    sealed interface Driveable permits Vehicle, Truck, Golfcart, Scooter {

    }

    public sealed interface Cartoon permits Flintstones {
    }

    public non-sealed interface Flintstones extends Cartoon {
    }

    /*
    if you implement a sealed interface the class must be marked with one of
    sealed, non-sealed, final
    */
    public sealed class Vehicle implements Driveable permits Car {
    }

    public sealed class Car extends Vehicle permits Ford, Volvo, Audi {
    }

    final class Ford extends Car {
    }

    final class Volvo extends Car {
    }

    non-sealed class Audi extends Car {
    }

    // no surprise you can extend a non-sealed class
    class RedAudi extends Audi {
    }

    // a sealed class MUST have subclasses
    public sealed class Scooter implements Driveable {
    }

    public final class Honda extends Scooter {
    }

    // non-sealed class doesn't need subclass
    public non-sealed static class Truck implements Driveable {
    }

    // no surprise that final class still doesn't need subclass
    // even if it implements a sealed interface
    public static final class Golfcart implements Driveable {
    }

}
