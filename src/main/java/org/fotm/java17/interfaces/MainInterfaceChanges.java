package org.fotm.java17.interfaces;

public class MainInterfaceChanges {

    public static void main(String[] args) {
        NoOverride noOverride = new NoOverride();

        System.out.println(noOverride.oldFashionedMethod());
        System.out.println(noOverride.defaultMethod());


        OverrideDefault od = new OverrideDefault();
        System.out.println(od.defaultMethod());
        System.out.println(od.oldFashionedMethod());

        System.out.println(NewStyleInterface.staticMethod());

        Phone phone = new Phone();
        phone.turnOn();
        phone.turnOff();
    }

}
