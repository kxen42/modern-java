package com.modernjava.domain;

import java.math.BigDecimal;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public record OrderDetails(String orderId,
                           Card card,
                           BigDecimal finalAmount) {
}
