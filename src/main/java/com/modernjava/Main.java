package com.modernjava;

import com.modernjava.domain.Card;
import com.modernjava.domain.CardType;
import com.modernjava.payment.CreditCardPayment;
import com.modernjava.payment.DebitCardPayment;
import com.modernjava.payment.PaymentLogger;
import com.modernjava.payment.RewardsCardPayment;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Card dbCard = new Card("Fred", "4629249031789528", "code", "12/28", CardType.DEBIT);
        var r = new DebitCardPayment().makePayment(dbCard, BigDecimal.TEN);
        PaymentLogger.logResponse(DebitCardPayment.class, r);

        Card cc = new Card("Wilma", "5490475030891121", "code", "12/28", CardType.CREDIT);
        r = new CreditCardPayment().makePayment(cc, BigDecimal.ONE);
        PaymentLogger.logResponse(CreditCardPayment.class, r);

        Card rewards = new Card("Betty", "5490475030892222", "code", "12/28", CardType.REWARDS);
        r = new RewardsCardPayment().makePayment(rewards, BigDecimal.valueOf(33.5));
        PaymentLogger.logResponse(RewardsCardPayment.class, r);
    }
}
