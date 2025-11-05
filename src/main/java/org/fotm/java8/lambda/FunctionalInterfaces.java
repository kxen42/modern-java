package org.fotm.java8.lambda;

import com.modernjava.domain.Card;
import com.modernjava.domain.CardType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Use lambda syntax to implement FunctionalInterfaces Original source <a
 * href="https://github.com/dilipsundarraj1/java-8/blob/master/java-8/src/com/learnJava/lambda/RunnableLambdaExample.java>pragmatic-code-school</a>
 */
public class FunctionalInterfaces {


  static void main() {
    functionalInterfaceRunnable();
    functionalInterfaceComparator();
    chainingFunctionalInterfaceImpls();
  }

  static void functionalInterfaceRunnable() {

    Runnable r1 = () -> System.out.println("ouch");

    Runnable r2 = () -> {
      System.out.println("no surprise here");
      System.out.println("boo");
    };

    new Thread(r1).start();
    new Thread(r2).start();
    new Thread(() -> System.out.println("as constructor arg")).start();
    new Thread(() -> System.out.println("Watch bob"), "bob").start();

  }

  static void functionalInterfaceComparator() {
    Comparator<Integer> biggestInt = (a, b) -> a - b;
    Integer a = 4;
    Integer b = 2;
    System.out.println(a.compareTo(b));
    System.out.println(biggestInt.compare(a, b));

  }

  static void chainingFunctionalInterfaceImpls() {

    Comparator<Card> cardNameComparator = (c1, c2) -> c1.name().compareTo(c2.name());
    Comparator<Card> cardCardTypeComparator = (c1, c2) -> c1.cardType().compareTo(c2.cardType());

    Card bob1 = new Card("bob", "42", "123", "12-12-2024", CardType.CREDIT);
    Card bob2 = new Card("bob", "43", "321", "12-12-2028", CardType.DEBIT);
    Card bob3 = new Card("bob", "4324", "3214", "12-12-2028", CardType.CREDIT);
    Card fred1 = new Card("fred", "14", "3214", "12-12-2028", CardType.CREDIT);
    Card fred2 = new Card("fred", "15", "3214", "12-12-2028", CardType.CREDIT);

    var result = cardNameComparator.compare(bob1, bob3);
    System.out.println("cardNameComparator " + result);
    result = cardCardTypeComparator.compare(bob1, bob3);
    System.out.println("cardCardTypeComparator " + result);

    result = cardNameComparator.thenComparing(cardCardTypeComparator).compare(bob1, bob3);
    System.out.println("chaining comparators " + result);

    result = cardNameComparator.thenComparing(cardCardTypeComparator).compare(bob1, bob2);
    System.out.println("chaining comparators " + result);

    result = cardNameComparator.thenComparing(cardCardTypeComparator).compare(bob2, bob1);
    System.out.println("chaining comparators " + result);

    // sorted using chaining comparators
    List.of(bob3, fred1, bob2, fred2, bob1).forEach(System.out::println);
    System.out.printf("Sort by card type then by card name%n");
    Stream.of(bob3, fred1, bob2, fred2, bob1)
        .sorted(cardCardTypeComparator.thenComparing(cardNameComparator))
        .forEach(System.out::println);
  }
}
