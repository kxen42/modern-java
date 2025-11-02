package org.fotm.java8.lambda;

@FunctionalInterface
public interface ArrayProcessor {

  int sum(int initial, int... varargs);

}
