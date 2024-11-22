package org.fotm.stream.assignment;

import org.fotm.model.Item;

import java.util.Comparator;
import java.util.List;

/**
 * Assignment (QID 2.1762)
 * <p>
 * 2. Given the {@code Item} class (in the zip file), declare a List typed for Item with the following Item’s:
 * <pre>
 * a. id=1 name=”Screw”
 * b. id=2 name=”Nail”
 * c. id=3 name=”Bolt”
 * </pre>
 * Stream the list and sort it so that it outputs “BoltNailScrew” i.e. alphabetic name order. Use Stream’s
 * {@code forEach} method to output the names (use the method reference version for the required Consumer
 * lambda).
 */
public class Q2_1762 {
    public static void main(String[] args) {
        List<Item> items = List.of(new Item(1, "Screw"), new Item(2, "Nail"), new Item(3, "Bolt"));

        items.stream()
             .sorted(Comparator.comparing(Item::getName, String::compareTo))
             .map(Item::getName)
             .forEach(System.out::print);
        System.out.println();
    }
}
