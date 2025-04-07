package com.modernjava.payment;

import com.modernjava.domain.CardType;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public final class PaymentFactory {

    private PaymentFactory() {
    }

    public static PaymentGateway paymentGateway(CardType type) {
        return switch (type) {
            case DEBIT -> new DebitCardPayment();
            case CREDIT -> new CreditCardPayment();
            case REWARDS -> new RewardsCardPayment();
            case null -> throw new IllegalArgumentException("Card type is required");
        };
    }
}
