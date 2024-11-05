package org.fotm.stream.collect;

import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class GroupingBy {
    public static void main(String[] args) {
        oneArg();
        twoArgsAndApplyCollector();
        threeArg();
    }

    /**
     * Find members of each surname.
     */
    public static void oneArg() {
        System.out.println(" ----- oneArg");
        List<User> users = UserGenerator.createUsers();

        Map<String, List<User>> collect = users.stream()
                                               .collect(groupingBy(
                                                   User::getLastName
                                               ));
        System.out.println(collect);
    }

    /**
     * Find average by surname.
     */
    public static void twoArgsAndApplyCollector() {
        System.out.println(" ----- twoArgsAndApplyCollector");
        List<User> users = UserGenerator.createUsers();

        Map<String, Double> collect = users.stream()
                                           .collect(groupingBy(
                                               User::getLastName,
                                               Collectors.averagingInt(User::getAge)
                                           ));
        System.out.println(collect);
    }

    /**
     * Find the distinct surnames for each city.
     * toSet silently skips duplicates
     */
    public static void threeArg() {
        System.out.println(" ----- threeArg");
        List<User> users = UserGenerator.createUsers();

        TreeMap<String, Set<String>> distinct = users.stream()
                                                     .collect(
                                                         groupingBy(User::getCity,
                                                                    TreeMap::new,
                                                                    mapping(User::getLastName, toSet())));

        System.out.println(distinct);

        TreeMap<String, List<User>> usersGroupByCity = users.stream()
                                                            .collect(
                                                                groupingBy(User::getCity,
                                                                           TreeMap::new,
                                                                           toList()));
        System.out.println(usersGroupByCity);
    }
}
