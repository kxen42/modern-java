package org.fotm.java17.methodreference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class MainStaticMethodReference {
    public static void main(String[] args) {
        staticMethodReferences();       // static
    }

    public static void staticMethodReferences(){
        // System.out::println is a case of static method reference

        //  Static method references are considered UNBOUND also. An example static method
        //  is Collections.sort(List)
        //
        //  Consumer takes one parameter => sort(List) is used, as opposed to sort(List, Comparator)
        // sort(List, Comparator) would need a BiFunction
        Consumer<List<Integer>> sortL  = list -> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;


        List<Integer> listOfNumbers = Arrays.asList(2,1,5,4,9);
        sortL.accept(listOfNumbers);// execution

        System.out.println(listOfNumbers);  // [1, 2, 4, 5, 9]


        listOfNumbers = Arrays.asList(8,12,4,3,7);
        sortMR.accept(listOfNumbers);// execution

        System.out.println(listOfNumbers);  // [3, 4, 7, 8, 12]
    }
}
