package com.modernjava.payment;

import com.modernjava.domain.Card;
import com.modernjava.domain.PaymentResponse;

import java.math.BigDecimal;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public sealed abstract class PaymentGateway permits CreditCardPayment, DebitCardPayment, RewardsCardPayment {

    public abstract PaymentResponse makePayment(Card card, BigDecimal amount);

}
