package org.fotm.java17.misc;

public class Global {
    public static void main(String[] args) {
        Global global = new Global();
        global.fxGlobal("global.main");
        StaticNestedClass staticNestedClass = new StaticNestedClass();
        staticNestedClass.fxStaticNestedClass("global.main");

        InnerClass innerClassInstance = global.new InnerClass();
        innerClassInstance.fxInnerClass("(global.)innerClassInstance.main");

    }

    void fxGlobal(String str) {
        System.out.println(" ----- fxGlobal " + str);
        InnerClass innerClassInstance = new InnerClass(); // create a Global.InnerClass
        innerClassInstance.fxInnerClass(str + ".fxGlobal");
        StaticNestedClass staticNestedClass = new StaticNestedClass();
        staticNestedClass.fxStaticNestedClass(str + ".fxGlobal");

        /* No can do
        class InnerClass extends InnerClass {} // circular definition

        {
            class Local {}
            {
                class Local {} // compile-time error
            }
            class Local {} // compile-time error
            class AnotherLocal {
                void bar() {
                    class Local {} // ok
                }
            }
        }
         */

        // this is in a method!!??
        class Local {
            public void fxLocal(String str) {
                System.out.println(" ----- fxLocal ");
                innerClassInstance.fxInnerClass(str + ".fxLocal");
            }
        } // ok, not in scope of prior Local

        Local localInstance = new Local();
        localInstance.fxLocal("fxGlobal");
        innerClassInstance.fxInnerClass("fxGlobal");

    }

    class InnerClass {// <--- inner class because it's non-static nested class
        public void fxInnerClass(String str) {
            System.out.println(" ----- fxInnerClass ");
            System.out.println(str + ".fxInnerClass");
        }
    }

    static class StaticNestedClass {
        public void fxStaticNestedClass(String str) {
            System.out.println(" ----- StaticNestedClass " + str);
            System.out.println(str + ".fxStaticNestedClass");
        }
    }
}
