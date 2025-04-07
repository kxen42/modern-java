package com.modernjava;

import com.modernjava.domain.Card;
import com.modernjava.payment.CreditCardPayment;
import com.modernjava.payment.DebitCardPayment;
import com.modernjava.payment.PaymentFactory;
import com.modernjava.payment.PaymentLogger;
import com.modernjava.payment.RewardsCardPayment;

import java.math.BigDecimal;

import static com.modernjava.domain.CardType.CREDIT;
import static com.modernjava.domain.CardType.DEBIT;
import static com.modernjava.domain.CardType.REWARDS;

public class Main {

    public static void main(String[] args) {
        Card dbCard = new Card("Fred", "4629249031789528", "code", "12/28", DEBIT);
        var r = PaymentFactory.paymentGateway(DEBIT)
                              .makePayment(dbCard, BigDecimal.TEN);
        PaymentLogger.logResponse(DebitCardPayment.class, r);

        Card cc = new Card("Wilma", "5490475030891121", "code", "12/28", CREDIT);
        r = PaymentFactory.paymentGateway(CREDIT)
                          .makePayment(cc, BigDecimal.ONE);
        PaymentLogger.logResponse(CreditCardPayment.class, r);

        Card rewards = new Card("Betty", "5490475030892222", "code", "12/28", REWARDS);
        r = PaymentFactory.paymentGateway(REWARDS)
                          .makePayment(rewards, BigDecimal.valueOf(33.5));
        PaymentLogger.logResponse(RewardsCardPayment.class, r);
    }
}
