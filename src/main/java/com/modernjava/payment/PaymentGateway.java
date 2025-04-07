package com.modernjava.payment;

import com.modernjava.domain.Card;
import com.modernjava.domain.PaymentResponse;

import java.math.BigDecimal;

public sealed abstract class PaymentGateway permits CreditCardPayment, DebitCardPayment, RewardsCardPayment {

    public abstract PaymentResponse makePayment(Card card, BigDecimal amount);

}
