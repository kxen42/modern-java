package org.fotm.java21.misc;

import org.fotm.java17.interfaces.Circle;
import org.fotm.java17.interfaces.Shape;
import org.fotm.java17.interfaces.Square;

public class SwitchExpression21 {
    public static void main(String[] args) {
        sealedClass(new Circle());
        sealedClass(new Square());
    }

    public static void sealedClass(Shape shape) {
        System.out.println(" ----- sealedClass");
        var result = switch(shape) {
            case Circle c -> c.name() + "K";
            case Square sq -> sq.name()  + "pants";
            default -> "default";
        };
        System.out.println("Look what I can do " + result);
    }
}
