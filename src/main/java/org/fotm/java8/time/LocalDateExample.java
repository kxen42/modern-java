package org.fotm.java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;

public class LocalDateExample {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println("now:\t\t\t\t\t\t\t\t\t"+now);
        System.out.println("get w/ ALIGNED_DAY_OF_WEEK_IN_MONTH:\t"+ now.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        System.out.println("chronology:\t\t\t\t\t\t\t\t"+now.getChronology());
        System.out.println("dayOfMonth:\t\t\t\t\t\t\t\t"+now.getDayOfMonth());
        System.out.println("dayOfWeek:\t\t\t\t\t\t\t\t"+now.getDayOfWeek());
        System.out.println("dayOfYear:\t\t\t\t\t\t\t\t"+now.getDayOfYear());
        System.out.println("era:\t\t\t\t\t\t\t\t\t"+now.getEra());
        System.out.println("long:\t\t\t\t\t\t\t\t\t"+now.getLong(ChronoField.YEAR_OF_ERA));
        System.out.println("month:\t\t\t\t\t\t\t\t\t" + now.getMonth());
        System.out.println("monthValue:\t\t\t\t\t\t\t\t" +now.getMonthValue());
        System.out.println("year:\t\t\t\t\t\t\t\t\t" + now.getYear());

        System.out.println("query:\t\t\t\t\t\t\t\t\t" + now.query(TemporalQueries.precision()));
        System.out.println("of:\t\t\t\t\t\t\t\t\t\t" + LocalDate.of(1999, 1, 23));

        ZonedDateTime zonedDateTime = now.atStartOfDay(ZoneId.of("Australia/Sydney"));
        System.out.println("Sydnay, AU:\t\t\t\t\t\t\t\t" + zonedDateTime);
        LocalDateTime localDateTime = now.atStartOfDay();
        System.out.println("atStartofDay:\t\t\t\t\t\t\t" + localDateTime);

        LocalDate ld = LocalDate.of(2000, 3, 12);
        System.out.println("Not a Leap Year:\t\t\t\t\t\t" + ld.isLeapYear());
        ld = LocalDate.of(2024,1,31);
        System.out.println("Leap Year:\t\t\t\t\t\t\t\t" +  ld.isLeapYear());
        ld = LocalDate.of(1999,1,31);
        System.out.println("Leap Year:\t\t\t\t\t\t\t\t" +  ld.isLeapYear());

    }
}
