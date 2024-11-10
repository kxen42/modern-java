package org.fotm.collections.oddballs;

import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.Collections;
import java.util.List;

public class Frequency {

    private static final List<User> users = UserGenerator.createUsers();

    public static void main(String[] args) {
        findTheDuplicateLastNames();
    }

    /**
     * {@code public static int frequency(Collection<?> c,
     * Object o)}
     * Returns the number of elements in the specified collection equal to the specified object.
     * More formally, returns the number of elements e in the collection such that Objects.equals(o, e).
     */
    public static void findTheDuplicateLastNames() {
        List<String> lastNames = users.stream()
                                      .map(User::getLastName)
                                      .toList();

        // frequency will process the entire stream of last names
        List<String> dupLastNames = lastNames.stream()
                                             .filter(name -> Collections.frequency(lastNames, name) > 1)
                                             .distinct()
                                             .toList();
        System.out.println(dupLastNames);

    }

}
