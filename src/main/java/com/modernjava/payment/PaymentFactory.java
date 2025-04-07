package com.modernjava.payment;

import com.modernjava.domain.CardType;

public final class PaymentFactory {

    private PaymentFactory() {
    }

    public static PaymentGateway paymentGateway(CardType type) {
        return switch (type) {
            case DEBIT -> new DebitCardPayment();
            case CREDIT -> new CreditCardPayment();
            case REWARDS -> new RewardsCardPayment();
        };
    }
}
