package org.fotm.stream.collect;

import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class Joining {
    static List<User> users = UserGenerator.createUsers();

    public static void main(String[] args) {
        noArg();
        oneArg("|");
        threeArg("|", "[", "]");
    }

    public static void noArg() {
        System.out.println(" ----- noArg");
        String collect = users.stream()
                              .map(User::getFirstName)
                              .collect(joining());
        System.out.println(collect);

    }

    public static void oneArg(CharSequence delimiter) {
        System.out.println(" ----- oneArg");
        String collect = users.stream()
                              .map(User::getFirstName)
                              .collect(joining(delimiter));
        System.out.println(collect);

    }

    public static void threeArg(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        System.out.println(" ----- threeArg");
        String collect = users.stream()
                              .map(User::getFirstName)
                              .collect(joining(delimiter, prefix, suffix));
        System.out.println(collect);

    }
}
