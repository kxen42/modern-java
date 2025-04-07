package com.modernjava;

import com.modernjava.domain.Card;
import com.modernjava.domain.CheckOutStatus;
import com.modernjava.domain.OrderDetails;
import com.modernjava.payment.CreditCardPayment;
import com.modernjava.payment.DebitCardPayment;
import com.modernjava.payment.PaymentFactory;
import com.modernjava.payment.PaymentLogger;
import com.modernjava.payment.PaymentService;
import com.modernjava.payment.RewardsCardPayment;
import com.modernjava.service.CheckoutService;

import java.math.BigDecimal;

import static com.modernjava.domain.CardType.CREDIT;
import static com.modernjava.domain.CardType.DEBIT;
import static com.modernjava.domain.CardType.REWARDS;

/**
 * This is all mine because I didn't want to write tests.
 */
public class Main {

    public static void main(String[] args) {
        // generate one of each type of card
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


        // create debut card order
        OrderDetails orderDetails = new OrderDetails("12345", dbCard, BigDecimal.valueOf(42.42));
        System.out.println(orderDetails);

        // call payment service for debit card
        PaymentService paymentService = new PaymentService();
        paymentService.makePaymentv2(orderDetails);

        // call checkout service
        CheckoutService checkoutService = new CheckoutService(paymentService);
        CheckOutStatus checkOutStatus = checkoutService.checkoutOrder(orderDetails);
        System.out.printf("Checkout service for order id %s, got response %s%n", orderDetails.orderId(),checkOutStatus);

    }
}
