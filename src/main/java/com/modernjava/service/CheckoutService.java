package com.modernjava.service;

import com.modernjava.domain.*;
import com.modernjava.payment.PaymentService;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public class CheckoutService {

    private final PaymentService paymentService;

    public CheckoutService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public CheckOutStatus checkoutOrder(OrderDetails orderDetails){

        var paymentResponse = paymentService.makePaymentv2(orderDetails);
        if(paymentResponse.equals(PaymentResponse.SUCCESS)){
            return CheckOutStatus.SUCCESS;
        }
        return CheckOutStatus.FAILURE;

    }
}
