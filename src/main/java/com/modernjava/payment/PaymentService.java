package com.modernjava.payment;

import com.modernjava.domain.OrderDetails;
import com.modernjava.domain.PaymentResponse;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public class PaymentService {

    public PaymentResponse makePaymentv2(OrderDetails orderDetails) {

        var paymentGateway = PaymentFactory.paymentGateway(orderDetails.card()
                                                                       .cardType());
        var r = paymentGateway.makePayment(orderDetails.card(), orderDetails.finalAmount());
        PaymentLogger.logResponse(paymentGateway.getClass(), r);
        return r;
    }
}
