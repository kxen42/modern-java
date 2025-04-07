package org.fotm.java17.patternmatching;

import org.fotm.java17.sealed.Decagon;
import org.fotm.java17.sealed.Polygon;
import org.fotm.java17.sealed.Hexagon;
import org.fotm.java17.sealed.Octagon;

public class RecordPatternMatching {

    static Hexagon hex;
    static Octagon oct;
    static Decagon deca;

    public static void main(String[] args) {
        instantiateRecords();
        doRecordPatternMatching();
        doWithNullDefault();
    }

    public static void instantiateRecords() {
        System.out.println(" ----- instantiateRecords");
         hex = new Hexagon("small", "bernie");
         oct = new Octagon("large", "yellow", "chet");
         deca = new Decagon("tiny", "gumball");
        System.out.printf("number of sides %-8s%s%nnumber of sides %-8s%s%n%s", hex.numberOfSides(), hex, oct.numberOfSides(), oct, deca);

    }

    /**
     * This can only be done with a record. It will fail to compile for a class.
     * @param poly a record implementing {@code Polygon}
     * @return some desription of poly
     */
    private static String recordPatternMatching(Polygon poly) {
        System.out.println(" ----- ----- recordPatternMatching");
        return switch (poly) {
            case null -> "(NIL)";
            case Hexagon(var size, var name) ->  name + " | " + size;
            case Octagon(var size, var color, var name) when color == null && name == null -> "using gaurded pattern order matters";
            case Octagon(var size, var color, var name) when color == null -> String.format("color: (NIL), name: %s", name);
            case Octagon(var size, var color, var name) when name == null -> String.format("color: %s, name: (NIL)", color);
            case Octagon(var size, var color, var name) -> name + " | " + size;
            case Decagon(var size, var name) -> String.format("name: %s", name);
            // don't need default because we have case for every permitted Polygon type
        };
    }

    private static String withNullDefault(Polygon poly) {
        System.out.println(" ----- ----- withNullDefault");
        return switch (poly) {
            case Hexagon(var size, var name) ->  name + " | " + size;
            case Octagon(var size, var color, var name) when color == null && name == null -> "using gaurded pattern order matters";
            case Octagon(var size, var color, var name) when color == null -> String.format("color: (NIL), name: %s", name);
            case Octagon(var size, var color, var name) when name == null -> String.format("color: %s, name: (NIL)", color);
            case Octagon(var size, var color, var name) -> name + " | " + size;
            case null, default ->  "poly: " + poly;
        };
    }

    public static void doRecordPatternMatching() {
        System.out.println(" ----- doRecordPatternMatching");
        System.out.println(recordPatternMatching(hex));
        System.out.println(recordPatternMatching(oct));
        System.out.println(recordPatternMatching(new Octagon("large", null, "betty")));
        System.out.println(recordPatternMatching(new Octagon("large", null, null)));
        System.out.println(recordPatternMatching(new Octagon("large", "cheeto orange", null)));
        System.out.println(recordPatternMatching(deca));
        System.out.println(recordPatternMatching(null));
    }

    public static void doWithNullDefault() {
        System.out.println(" ----- doWithNullDefault");
        System.out.println(withNullDefault(deca));
        System.out.println(withNullDefault(null));
    }
}
