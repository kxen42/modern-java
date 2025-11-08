package org.fotm.java8.datetime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public class LocalX {

  private static final ZoneId zoneId = ZoneId.of("Australia/Sydney");

  static void main() {
    localDate();
    localTime();
    localDateTime();
    usingClock();
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
}
