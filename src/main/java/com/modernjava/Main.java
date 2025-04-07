package com.modernjava;

import com.modernjava.domain.Card;
import com.modernjava.domain.CardType;
import com.modernjava.payment.DebitCardPayment;
import com.modernjava.payment.PaymentGateway;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Card dbCard = new Card("Fred", "4515-1234-2345-678", "code", "12/28", CardType.DEBIT);
       var dcPayment = new DebitCardPayment();
        dcPayment.makePayment(dbCard, BigDecimal.TEN);
    }
}
