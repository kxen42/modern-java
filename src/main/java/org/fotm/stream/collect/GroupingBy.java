package org.fotm.stream.collect;

import org.fotm.model.Member;
import org.fotm.model.User;
import org.fotm.model.UserGenerator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class GroupingBy {


    private static final List<User> users = UserGenerator.createUsers();

    public static void main(String[] args) {
        oneArg();
        twoArgsAndApplyCollector();
        threeArg();
        groupByAge();
        determineNumberOfUsersByAge();
        findDistinctOrdersSurnamesForCity();
        mapOfLists();
        mapOfMaps();
        customKeyValuePair();
        customCollector();
        gatherOnlyTheDuplicateLastNamesAndCount();
        gatherOnlyTheDuplicateLastNames();
    }

    /**
     * Find members of each surname.
     */
    public static void oneArg() {
        System.out.println(" ----- oneArg");

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


    public static void groupByAge() {
        System.out.println(" ----- groupByAge");

        Map<Integer, List<User>> groupedByAge = users.stream()
                                                     .collect(groupingBy(User::getAge));
        groupedByAge.forEach((age, theUsers) -> System.out.println("Age: " + age + ", users:" + theUsers));
    }

    public static void determineNumberOfUsersByAge() {
        System.out.println(" ----- determineNumberOfUsersByAge");
        Map<Integer, Long> numUsersByAge = users.stream()
                                                .collect(groupingBy(
                                                    User::getAge,
                                                    Collectors.counting()
                                                ));

        numUsersByAge.forEach((age, count) ->
                                  System.out.println("Age: " + age + ", count: " + count));
    }

    public static void findDistinctOrdersSurnamesForCity() {
        System.out.println(" ----- findDistinctSurnamesForCity");

        Map<String, TreeSet<String>> sortedSurnamesByCity = users.stream()
                                                                 .collect(groupingBy(
                                                                     User::getCity,
                                                                     mapping(User::getLastName, toCollection(TreeSet::new))
                                                                 ));
        sortedSurnamesByCity.forEach((city, surnames) -> System.out.println("City: " + city + ", surnames: " + surnames));

    }

    public static void mapOfLists() {
        System.out.println(" ----- mapOfLists");
        HashMap<BigDecimal, List<String>> collect = users.stream()
                                                         .collect(groupingBy(
                                                             (u) -> u.getMemberFee() == null ? BigDecimal.ZERO : u.getMemberFee(),
                                                             HashMap::new,
                                                             mapping((uu) -> String.join(" ", uu.getFirstName(), uu.getLastName()),
                                                                     toList())));
        /*
        {0=[Patrick Star, SpongeBob Squarepants],
        19.99=[Elroy Jetson, Jane Jetson, Judy Jetson],
        99.99=[Wilma Flintstone, Betty Rubble, Barney Rubble],
        199.99=[Fred Flintstone, George Jetson, Sandy Squirrel]}
         */
        System.out.println(collect);
    }

    public static void mapOfMaps() {
        System.out.println(" ----- mapOfMaps");
        Map<BigDecimal, TreeMap<String, BigDecimal>> collect = users.stream()
                                                                    .collect(groupingBy((user) -> user.getMemberFee() == null ? BigDecimal.ZERO : user.getMemberFee(),
                                                                                        toMap(
                                                                                            (u) -> u.getFirstName() + " " + u.getLastName(),
                                                                                            (user) -> user.getMemberFee() == null ? BigDecimal.ZERO : user.getMemberFee(),
                                                                                            (memberFee, bigDecimal) -> memberFee, TreeMap::new)));
        /*
            {
            0={Patrick Star=0, SpongeBob Squarepants=0},
            19.99={Elroy Jetson=19.99, Jane Jetson=19.99, Judy Jetson=19.99},
            99.99={Barney Rubble=99.99, Betty Rubble=99.99, Wilma Flintstone=99.99},
            199.99={Fred Flintstone=199.99, George Jetson=199.99, Sandy Squirrel=199.99}
            }
        */
        System.out.println(collect);
    }

    public static void customKeyValuePair() {
        // Grouping by Age and Collecting to a Map with Custom Key-Value Pair
        System.out.println(" ----- customKeyValuePair");

        Map<Integer, Map<String, Integer>> groupedByAgeAndNamesMap = users.stream()
                                                                          .collect(groupingBy(
                                                                              User::getAge,
                                                                              toMap(User::getFirstName, User::getAge)));

        /*
        {
        2={Sandy=2},
        34={Wilma=34, Betty=34},
        35={Barney=35}, 36={Fred=36},
        5={Patrick=5, SpongeBob=5},
        39={Jane=39},
        8={Elroy=8},
        45={George=45},
        15={Judy=15}}
         */
        System.out.println(groupedByAgeAndNamesMap);
    }

    public static void customCollector() {
        //Grouping by Age and Collecting to a Map with Custom Collector
        // Processing Users to create Members grouped by age
        System.out.println(" ----- customCollector");
        Map<Integer, Map<String, Member>> groupedByAgeAndNamesCustomCollector = users.stream()
                                                                                     .collect(
                                                                                         groupingBy(
                                                                                             User::getAge,
                                                                                             toMap(u1 -> String.join(" ", u1.getFirstName(), u1.getLastName()),
                                                                                                   u -> Member.builder()
                                                                                                              .name(String.join(" ", u.getFirstName(), u.getLastName()))
                                                                                                              .age(2)
                                                                                                              .fee(BigDecimal.TEN)
                                                                                                              .build())));


        /*
        Age: 2; Members: {Sandy Squirrel=Member(name=Sandy Squirrel, age=2, fee=199.99)}
        Age: 34; Members: {Betty Rubble=Member(name=Betty Rubble, age=34, fee=99.99), Wilma Flintstone=Member(name=Wilma Flintstone, age=34, fee=99.99)}
        Age: 35; Members: {Barney Rubble=Member(name=Barney Rubble, age=35, fee=99.99)}
        Age: 36; Members: {Fred Flintstone=Member(name=Fred Flintstone, age=36, fee=199.99)}
        Age: 5; Members: {Patrick Star=Member(name=Patrick Star, age=5, fee=null), SpongeBob Squarepants=Member(name=SpongeBob Squarepants, age=5, fee=null)}
        Age: 39; Members: {Jane Jetson=Member(name=Jane Jetson, age=39, fee=19.99)}
        Age: 8; Members: {Elroy Jetson=Member(name=Elroy Jetson, age=8, fee=19.99)}
        Age: 45; Members: {George Jetson=Member(name=George Jetson, age=45, fee=199.99)}
        Age: 15; Members: {Judy Jetson=Member(name=Judy Jetson, age=15, fee=19.99)}
         */
        groupedByAgeAndNamesCustomCollector.forEach((age, members) -> System.out.println("Age: " + age + "; Members: " + members));

    }

    public static void gatherOnlyTheDuplicateLastNamesAndCount() {
        System.out.println(" ----- gatherOnlyTheDuplicateLastNames");
        Stream<String> lastNames = UserGenerator.createUsers()
                                                .stream()
                                                .map(User::getLastName);

        Map<String, Long> lastNameCount = lastNames.collect(groupingBy(Function.identity(), Collectors.counting()));
        lastNameCount.entrySet()
                     .stream()
                     .filter(entry -> entry.getValue() > 1)
                     .toList()
                     .forEach(System.out::println);
    }

    public static void gatherOnlyTheDuplicateLastNames() {
        System.out.println(" ----- gatherOnlyTheDuplicateLastNames");
        Stream<String> lastNames = UserGenerator.createUsers()
                                                .stream()
                                                .map(User::getLastName);

        Map<String, Long> lastNameCount = lastNames.collect(groupingBy(Function.identity(), Collectors.counting()));
        lastNameCount.entrySet()
                     .stream()
                     .filter(entry -> entry.getValue() > 1)
                     .map(Map.Entry::getKey)
                     .sorted()
                     .toList()
                     .forEach(System.out::println);
    }
}
