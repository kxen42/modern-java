package org.fotm.stream.collect;

import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ToMap {

    public static void main(String[] args) {
        twoArgToMap();
        threeArgToMap();
        fourArgToMap();
        keyOnLengthOfString();
    }

    /**
     * Confident there is now duplicate keys.
     */
    public static void twoArgToMap() {
        System.out.println(" ----- twoArgToMap");
        List<User> users = UserGenerator.createUsers();

        // map user to ID
        Map<UUID, User> collect = users.stream()
                                       .collect(toMap(
                                           User::getId,
                                           Function.identity()
                                       ));

        System.out.println(collect);
    }

    /**
     * Handle duplicate keys.
     */
    public static void threeArgToMap() {
        System.out.println(" ----- threeArgToMap");
        List<User> users = UserGenerator.createUsers();

        Map<String, String> collect = users.stream()
                                           .collect(toMap(
                                               User::getLastName,
                                               User::getFirstName,
                                               (u1, u2) -> String.join(", ", u1, u2))
                                           );

        System.out.println(collect);
    }

    /**
     * Handle duplicate keys.
     * Specify Map type.
     */
    public static void fourArgToMap() {
        System.out.println(" ----- fourArgToMap");
        List<User> users = UserGenerator.createUsers();

        TreeMap<String, String> collect = users.stream()
                                               .collect(toMap(
                                                   User::getLastName,
                                                   User::getFirstName,
                                                   (u1, u2) -> String.join(", ", u1, u2),
                                                   TreeMap::new)
                                               );

        System.out.println(collect);
    }

    public static void keyOnLengthOfString() {
        // use string length as key, string as value
        // have keys sorted
        System.out.println(" ----- keyOnLengthOfString");


        TreeMap<Integer, String> collect = Stream.of("a", "abc", "def", "h", "uvwxyz")
                                                 .collect(toMap(
                                                     String::length,
                                                     Function.identity(),
                                                     (s1, s2) -> String.join("|", s1, s2),
                                                     TreeMap::new
                                                 ));
        System.out.println(collect);
    }
}
