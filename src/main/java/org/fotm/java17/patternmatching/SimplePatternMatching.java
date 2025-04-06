package org.fotm.java17.patternmatching;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Since Java 16
 */
public class SimplePatternMatching {

    public static void main(String[] args) {
        var pm = new SimplePatternMatching();
        pm.doInstanceOfMatch(pm);
        pm.doSwitchPatternMatching(pm);
    }

    /**
     * Type pattern matching with binding variable.
     * @param o Object
     */
    public void typePatternUsingInstanceOf(Object o) {
        System.out.println(" ----- typePatternUsingInstanceOf");
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

    /**
     * Replace if-else and intanceof with enhances switch.
     * Notice how null and default are handled.
     * @param o Object
     * @return string with type name and stringTo of o
     */
    public String switchPatternMatching(Object o) {
        System.out.println(" ----- switchPatternMatching");
        var r = switch (o) {
            case String s -> "String " + s;
            case BigInteger b -> "BigInteger " + b;
            case BigDecimal b -> "BigDecimal " + b;
            case Long i -> "long " + i;
            case null, default -> "null or no match " + o;
        };
        System.out.printf("result %s%n", r);
        return r;
    }

    /**
     * Invoke type pattern matching with once of each type.
     * @param pm instance with the method
     */
    public void doSwitchPatternMatching(SimplePatternMatching pm) {
        System.out.println(" ----- doSwitchPatternMatching");
        System.out.printf("%s%n%s%n%s%n%s%n%s%n",
                          pm.switchPatternMatching("bob"),
                          pm.switchPatternMatching(BigDecimal.valueOf(19.99)),
                          pm.switchPatternMatching(2L),
                          pm.switchPatternMatching(1),
                          pm.switchPatternMatching(null));
    }

    /**
     * Type pattern matching with if-else chain.
     * @param pm instance with method
     */
    public void doInstanceOfMatch(SimplePatternMatching pm) {
        System.out.println(" ----- doInstanceOfMatch");

        pm.typePatternUsingInstanceOf(BigDecimal.valueOf(3.52));
        pm.typePatternUsingInstanceOf(BigInteger.valueOf(3));
        pm.typePatternUsingInstanceOf("Boo");
        pm.typePatternUsingInstanceOf(null);
    }

}
