package org.fotm.java8.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Formatting {

  static void main() {
    usingBuilder();
    isoDates();
    optionalSeconds();
    textEscape();

  }

  public static void usingBuilder() {
    System.out.println(" ----- usingBuilder");

    ;
    var formatter = new DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        .appendLiteral(" ")
        .append(DateTimeFormatter.ofPattern("HH.mm.ss.SSS"))
        .appendLiteral(" ")
        .appendZoneRegionId()
        .toFormatter();

    String string = formatter.format(LocalDateTime.now().atZone(ZoneId.systemDefault()));
    System.out.println("formatted LocalDateTime " + string);

    ZonedDateTime zonedDateTime = ZonedDateTime.parse(string, formatter);
    System.out.println("parsed zoned date time " + zonedDateTime);

    var input = "2025/11/09 13.45.51.999 Pacific/Honolulu";
    zonedDateTime = ZonedDateTime.parse(input, formatter);
    System.out.println("formatted zoned date time " + zonedDateTime);

    ZonedDateTime hawaii = Instant.now().atZone(ZoneId.of("Pacific/Honolulu"));
    String formatted = formatter.format(hawaii);
    System.out.println("Hawaii " + hawaii);
  }

  public static void isoDates() {
    System.out.println(" ----- isoDates");
    var str = "19991231";
    LocalDate parsed = LocalDate.parse(str, DateTimeFormatter.BASIC_ISO_DATE);
    System.out.println("parsed " + parsed);
  }

  public static void optionalSeconds() {
    System.out.println(" ----- optionalSeconds");
    var formatter = new DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ISO_DATE)
        .appendLiteral(" ")
        .appendValue(ChronoField.HOUR_OF_DAY, 2)
        .appendLiteral(".")
        .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
        .optionalStart()
        .appendLiteral(".")
        .appendValue(ChronoField.SECOND_OF_MINUTE)
        .optionalEnd()
        .toFormatter();

    System.out.println("optional seconds " + formatter.format(LocalDateTime.now()));

    System.out.println("parsed " + LocalDateTime.parse("1999-12-31 23.59", formatter));
  }

  public static void textEscape() {
    System.out.println(" ----- textEscape");
    var str = "12/31/1999 it's garbage 11:01";
   var formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy' it''s garbage 'hh:mm");
    String ldt = LocalDateTime.now().format(formatter);
    System.out.println("formatted with text escape " + ldt);
    System.out.println("parsed with text escape " + formatter.parse(str));
  }
}
