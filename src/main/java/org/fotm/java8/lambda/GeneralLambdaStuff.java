package org.fotm.java8.lambda;

import java.util.Comparator;

public class GeneralLambdaStuff {

  static void main() {
    // this produces a void result
    Runnable runnable = () -> {};

    // unnamed lambda parameter '_' is new to Java 25

    argumentArity();
  }

  static void argumentArity() {



    // variable arity is treated as array
    // you can't use Integer on the parameters - there is no autoboxing
    ArrayProcessor apLambda = (int y, int... x) -> {
      var result = y;
      for (int j : x) {
        result += j;
      }
      return result;
    };
    System.out.println(apLambda.sum(1, 2, 3, 4));


  }


}
