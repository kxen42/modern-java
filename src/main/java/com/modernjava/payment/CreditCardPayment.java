package com.modernjava.payment;

import com.modernjava.domain.Card;
import com.modernjava.domain.CardType;
import com.modernjava.domain.PaymentResponse;

import java.math.BigDecimal;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public final class CreditCardPayment extends PaymentGateway {

    @Override
    public PaymentResponse makePayment(Card card, BigDecimal amount) {
        PaymentLogger.logAmount(CardType.CREDIT, amount);
        return PaymentResponse.SUCCESS;
    }
}
