package org.fotm.java8.datetime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;

public class LocalX {

  private static final ZoneId zoneId = ZoneId.of("Australia/Sydney");

  static void main() {
    localDate();
    localTime();
    localDateTime();
    usingClock();
    utilDate();
    sqlDate();
  }

  public static void localDate() {
    System.out.println(" ----- localDate");
    System.out.printf("local date %s, Sydney date %s%n", LocalDate.now(), LocalDate.now(zoneId));

    try {
      LocalDate.now().get(ChronoField.MINUTE_OF_HOUR);
    } catch (UnsupportedTemporalTypeException e) {
      System.out.printf("LocalDate and LocalTime throw %s for TemporalFields that make no sense.", e.getClass());;
    }
  }
  public static void localTime() {
    System.out.println(" ----- localTime");
    System.out.printf("local time %s, Sydney time %s%n", LocalTime.now(), LocalTime.now(zoneId));
  }
  public static void localDateTime() {
    System.out.println(" ----- localDateTime");
    System.out.printf("local date/time %s, Sydney date/time %s%n", LocalDateTime.now(), LocalDateTime.now(zoneId));

  }

  public static void usingClock() {
    System.out.println(" ----- usingClock");
    // Clock is used for testing like the Clock used by RBC
    Instant baseInstant = Instant.parse("2007-12-03T10:15:30.00Z");
    Clock baseClock = Clock.fixed(baseInstant, ZoneId.of("Australia/Sydney"));
    Clock earlier = Clock.offset(baseClock, Duration.ofHours(-2));
    Clock later = Clock.offset(baseClock, Duration.ofDays(2));
    System.out.println("baseClock " + baseClock);
    System.out.println("earlier " + earlier);
    System.out.println("later " + later);

    System.out.printf("earlier %s, base %s, later %s%n", LocalDateTime.now(earlier), LocalDateTime.now(baseClock), LocalDateTime.now(later));
  }

  public static void utilDate() {
    System.out.println(" ----- utilDate");
    Date utilDate = new Date();
    System.out.println("java.util.Date " + utilDate);

    // convert java.util.Date to LocalDate
    LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    System.out.println("java.time.LocalDate " + localDate);
    LocalDateTime localDateTime = utilDate.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    System.out.println("java.time.LocalDateTime " + localDateTime);
    LocalTime localTime = utilDate.toInstant().atZone(ZoneId.systemDefault())
        .toLocalTime();
    System.out.println("java.time.LocalTime " + localTime);

    // yuck LocalTime/Instant to ancient Date
    Date date = Date.from(LocalDate.now().atTime(LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant());
    System.out.println("java.time.Local* to java.util.Date " + date);
  }

  public static void sqlDate() {
    System.out.println(" ----- sqlDate");
    var date = java.sql.Date.valueOf(LocalDate.now());
    System.out.println("java.sql.Date " + date);

    // convert java.util.Date to LocalDate
    // Note the use of atZone on the Instant
    LocalDate localDate = date.toLocalDate();
    System.out.println("java.time.LocalDate " + localDate);

    try {
      date.toInstant();
    } catch (UnsupportedOperationException _) {
      System.out.printf("java.sql.Date doesn't have any time date%n\ttoInstant throws UnsupportedOperationException%n");
    }
  }
}
