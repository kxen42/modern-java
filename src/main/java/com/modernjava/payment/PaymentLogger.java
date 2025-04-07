package com.modernjava.payment;

import com.modernjava.domain.CardType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PaymentLogger {


    public static void logAmount(CardType cardType, BigDecimal amount) {
        var a = amount.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.printf("Making %s card payment in the amount of: $%.2f%n", cardType.name().toLowerCase(), a);
    }
}
