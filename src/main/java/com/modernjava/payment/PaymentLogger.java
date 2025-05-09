package com.modernjava.payment;

import com.modernjava.domain.CardType;
import com.modernjava.domain.PaymentResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This all my stuff.
 */
public final class PaymentLogger {


    private PaymentLogger() {
    }

    public static void logAmount(CardType cardType, BigDecimal amount) {
        var a = amount.setScale(2, RoundingMode.HALF_UP)
                      .doubleValue();
        System.out.printf("Making %s card payment in the amount of: $%.2f%n", cardType.name()
                                                                                      .toLowerCase(), a);
    }

    public static void logResponse(Class<?> clazz, PaymentResponse response) {
        System.out.printf("Making %s, got response: %s%n", clazz.getSimpleName(), response);
    }
}
