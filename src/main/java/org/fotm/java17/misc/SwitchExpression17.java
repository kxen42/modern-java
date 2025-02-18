package org.fotm.java17.misc;

import java.time.Month;
import java.time.Year;

public class SwitchExpression17 {
    public static void main(String[] args) {
        switchStatement(Month.FEBRUARY, 2004);
        switchStatement(Month.FEBRUARY, 2001);
        switchStatement(Month.FEBRUARY, 2000);
        switchExpression(Month.JANUARY, 1965);
        switchExpression(Month.AUGUST, 1966);
        usingYield(Month.FEBRUARY, 1964);
        usingColon(Month.FEBRUARY, 1980);
    }


    public static void switchStatement(Month month, int year) {
        System.out.println(" ----- switchStatement");
        var numDays = 0;
        switch (month) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                numDays = 30;
                break;
            case FEBRUARY:
                numDays = Year.isLeap(year) ? 29 : 28;
                break;
            default:
                numDays = 31;


        }

        System.out.println("number of days for month: " + month + ", year: " + year + " is " + numDays);
    }

    public static void switchExpression(Month month, int year) {
        System.out.println(" ----- switchExpression");
        var numDays = switch(month) {
            case  APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> Year.isLeap(year) ? 29 : 28;
            default -> 31;
        };

        System.out.println("number of days for month: " + month + ", year: " + year + " is " + numDays);
    }

    public static void usingYield(Month month, int year) {
        System.out.println(" ----- usingYield");
        var numDays = switch(month) {
            case  APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> {
                var msg = "Determine if %d is a leap year.".formatted(year);
                System.out.println(msg);
                yield  Year.isLeap(year) ? 29 : 28;
            }  // <-- no ;
            default -> 31;
        };

        System.out.println("number of days for month: " + month + ", year: " + year + " is " + numDays);
    }

    /*
    public static void wontCompile(Month month, int year) {
        var numDays = switch(month) {
            case  APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> {
                var msg = "Determine if %d is a leap year.".formatted(year);
                System.out.println(msg);
                return Year.isLeap(year) ? 29 : 28;
            }  // <-- not ;
            default -> 31;
        };

        System.out.println("number of days for month: " + month + ", year: " + year + " is " + numDays);
    }
    */

    public static void usingColon(Month month, int year) {
        System.out.println(" ----- usingColon");
        var numDays = switch(month) {
            case  APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER: yield 30;
            case FEBRUARY:
                var msg = "Determine if %d is a leap year.".formatted(year);
                System.out.println(msg);
                yield Year.isLeap(year) ? 29 : 28;
            default: yield 31;
        };

        System.out.println("number of days for month: " + month + ", year: " + year + " is " + numDays);
    }

}
