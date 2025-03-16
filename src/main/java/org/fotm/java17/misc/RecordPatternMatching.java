package org.fotm.java17.misc;

import org.fotm.java17.interfaces.Polygon;
import org.fotm.java17.interfaces.Hexagon;
import org.fotm.java17.interfaces.Octagon;

public class RecordPatternMatching {

    public static void main(String[] args) {
        lookWhatICanDo();
        doRecordPatternMatching();
    }

    public static void lookWhatICanDo() {
        var hex = new Hexagon("small", "bernie");
        var oct = new Octagon("large", "yellow", "chet");
        System.out.printf("number of sides %-8s%s%nnumber of sides %-8s%s%n", hex.numberOfSides(), hex, oct.numberOfSides(), oct);

    }

    private static String recordPatternMatching(Polygon poly) {
        return switch (poly) {
            case null -> "(NIL)";
            case Hexagon(var size, var name) -> name;
            case Octagon(var size, var color, var name) -> name;
            // don't need default because we have case for every permitted Polygon type
        };
    }

    public static void doRecordPatternMatching() {
        System.out.println(" ----- doRecordPatternMatching");
        System.out.println(recordPatternMatching(new Hexagon("small", "fred")));
        System.out.println(recordPatternMatching(new Octagon("large", "yellow", "barney")));
    }
}
