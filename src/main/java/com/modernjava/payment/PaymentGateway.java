package com.modernjava.payment;

import com.modernjava.domain.Card;
import com.modernjava.domain.PaymentResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

public sealed abstract class PaymentGateway permits DebitCardPayment {

    public abstract PaymentResponse makePayment(Card card, BigDecimal amount);

}
