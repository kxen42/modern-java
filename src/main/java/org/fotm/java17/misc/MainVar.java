package org.fotm.java17.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * The stuff you can do with 'var'. A dubious introduction to Java 10.
 *
 * The {@code var} key instead of the can be used with local variables
 * that must be initialized on the line where they are declared so
 * the knows what type to infer.
 *
 * Detailed comments from Dr. Sean Kennedy
 */
public class MainVar {

    // 1. Where they can be used:
    //      - constructors, methods or init blocks
    // 2. Where they CANNOT be used
    //      - constructors/methods parameters or instance/class variables ("local")
    //      - caveat: lambdas where the parameter type can be inferred!
    // 3. A var is always initialised on the same statement where it is declared.
    //      e.g. var x = 8;
    // 4. The value of var can change but the type cannot.
    // 5. var cannot be simply initialised to null; cast the null to a type first.
    // 6. var not allowed in multi-variable declarations.
    // 7. var is a reserved name but not a reserved word
    //      - var var = 8; //ok
    //      - identifiers can be called var but not type names e.g. classes, interfaces, enums.

    static {
        var theAnswer = 42;
        System.out.println(theAnswer);
    }

    { // instance initialization block
        var d = 4.5;    // 1. 'd' is now double
        d=98.9;         // 4. value can change
    }

    public static void main(String[] args) {
        var var = 8; // identifiers can be called var but not type names e.g. classes, interfaces, enums.
        var x = 2;      // 1.
        var y = 42.0;   // 1.
        var s = "abc";  // 1.
        m(1);
    }
    public static void m(int x){    // 2.

        var s = (String)null;     // 5. cast null to a type first
        int a=3, b=4;       // 6.
        var var=9;          // 7.

        List<String> ls = new ArrayList<>();        // 2.
        ls.add("a");
        ls.add("a");
        ls.add("b");
        ls.removeIf( (var str) -> str                                                                                                               .equals("a")); // Predicate, removes every "a"
        System.out.println(ls); // [b]
    }
}

