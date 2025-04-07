package com.modernjava.payment;

import com.modernjava.domain.Card;
import com.modernjava.domain.CardType;
import com.modernjava.domain.PaymentResponse;

import java.math.BigDecimal;

public final class CreditCardPayment extends PaymentGateway {

    @Override
    public PaymentResponse makePayment(Card card, BigDecimal amount) {
        PaymentLogger.logAmount(CardType.CREDIT, amount);
        return PaymentResponse.SUCCESS;
    }
}
