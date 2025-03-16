package org.fotm.java17.misc;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Since Java 16
 */
public class PatternMatching {

    public static void main(String[] args) {
        var pm = new PatternMatching();
        pm.doInstanceOfMatch(pm);
        pm.doSwitchPatternMatching(pm);
    }

    public void typePatternUsingInstanceOf(Object o) {
        if (o instanceof BigDecimal bd) {
            System.out.println(bd.add(BigDecimal.TWO));
        } else if (o instanceof BigInteger bi) {
            System.out.println(bi.add(bi.add(BigInteger.TEN)));
        } else if (o != null) {
            System.out.println(o.hashCode());
        } else {
            System.out.println("(NIL)");
        }
    }

    public String switchPatternMatching(Object o) {
        return switch (o) {
            case String s -> "String " + s;
            case BigInteger b -> "BigInteger " + b;
            case BigDecimal b -> "BigDecimal " + b;
            case Long i -> "long " + i;
            case null, default -> "null or no match " + o;
        };
    }

    public void doSwitchPatternMatching(PatternMatching pm) {
        System.out.println(" ----- doSwitchPatternMatching");
        System.out.printf("%s%n%s%n%s%n%s%n%s%n",

                          pm.switchPatternMatching("bob"),
                          pm.switchPatternMatching(BigDecimal.valueOf(19.99)),
                          pm.switchPatternMatching(2L),
                          pm.switchPatternMatching(1),
                          pm.switchPatternMatching(null));
    }

    public void doInstanceOfMatch(PatternMatching pm) {
        System.out.println(" ----- doInstanceOfMatch");

        pm.typePatternUsingInstanceOf(BigDecimal.valueOf(3.52));
        pm.typePatternUsingInstanceOf(BigInteger.valueOf(3));
        pm.typePatternUsingInstanceOf("Boo");
        pm.typePatternUsingInstanceOf(null);
    }

}
