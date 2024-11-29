package org.fotm.java17.misc;

public interface NewInterfaceFeatures {

    private void privateMethod() {
        System.out.println(" ----- privateMethod");
    }

//    private default void privateDefailtMethod() {
//        // not allowed
//    }

    default void defaultMethod() {
        System.out.println(" ----- defaultMethod");
    }

    private static void privateStaticMethod() {
        System.out.println(" ----- privateStaticMethod");
    }

    static void publicStaticMethod() {
        System.out.println(" ----- publicStaticMethod");
    }

    default void callInsideInterface() {
        System.out.println(" ----- callInsideInterface");
        privateMethod();
        defaultMethod();
        privateStaticMethod();
        publicStaticMethod();
        System.out.println("-------------------------");
    }
    
    // Nested interface in and interface must be public static 
    // public static is redundant here
    interface NestedInterfaceInInterface {
        static void publicStaticMethod() {
            System.out.println(" -----  NestedInterfacepublicStaticMethod");
        }
        
        private static void privateStaticMethod() {
            System.out.println(" ----- NestedInterface privateStaticMethod");
        }
    }
}
