package org.fotm.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

public class MemberGenerator {

    public static List<Member> createMembers() {
        Map<String, Course> courses = createCourses();
        return List.of(
            Member.builder()
                  .age(24)
                  .courses(
                      List.of(
                          courses.get("Basket Weaving"),
                          courses.get("Greek")
                      )
                  )
                  .fee(BigDecimal.TEN)
                  .name("Hugo")
                  .build(),
            Member.builder()
                  .age(37)
                  .courses(
                      List.of(
                          courses.get("Basket Weaving"),
                          courses.get("Greek")
                      )
                  )
                  .fee(new BigDecimal("49.99"))
                  .name("Sam")
                  .build(),
            Member.builder()
                  .age(20)
                  .courses(
                      List.of(
                          courses.get("Latin"),
                          courses.get("Greek")
                      )
                  )
                  .fee(BigDecimal.TEN)
                  .name("Nicole")
                  .build(),
            Member.builder()
                  .age(20)
                  .courses(
                      List.of(
                          courses.get("Latin")
                      )
                  )
                  .fee(BigDecimal.ONE)
                  .name("Kate")
                  .build(),
            Member.builder()
                  .age(20)
                  .courses(
                      List.of(
                          courses.get("Latin")
                      )
                  )
                  .fee(BigDecimal.ONE)
                  .name("Russel")
                  .build()
        );

    }

    private static Map<String, Course> createCourses() {
        Map<String, Course> courses = new TreeMap<>();

        Map<String, LocalDateTime> sections = new TreeMap<>();
        sections.put("Basket Weaving A", LocalDateTime.of(2024, 11, 11, 12, 0, 0));
        sections.put("Basket Weaving B", LocalDateTime.of(2024, 11, 11, 13, 0, 0));
        sections.put("Latin A", LocalDateTime.of(2024, 11, 11, 9, 0, 0));
        sections.put("Greek A", LocalDateTime.of(2024, 11, 11, 9, 0, 0));
        sections.put("Greek B", LocalDateTime.of(2024, 11, 11, 10, 0, 0));
        sections.put("Greek C", LocalDateTime.of(2024, 11, 11, 11, 0, 0));

        Course course = Course.builder()
                              .name("Basket Weaving")
                              .sections(sections.entrySet()
                                                .stream()
                                                .filter(e -> e.getKey()
                                                              .contains("Basket Weaving"))
                                                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)))
                              .build();
        courses.put(course.getName(), course);
        course = Course.builder()
                       .name("Latin")
                       .sections(sections.entrySet()
                                         .stream()
                                         .filter(e -> e.getKey()
                                                       .contains("Latin"))
                                         .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)))
                       .build();
        courses.put(course.getName(), course);
        course = Course.builder()
                       .name("Greek")
                       .sections(sections.entrySet()
                                         .stream()
                                         .filter(e -> e.getKey()
                                                       .contains("Greek"))
                                         .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)))
                       .build();
        courses.put(course.getName(), course);

        return courses;
    }
}
