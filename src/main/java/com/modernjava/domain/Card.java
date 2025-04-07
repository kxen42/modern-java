package com.modernjava.domain;

/**
 * From Udemy course
 * <a href="https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/learn/lecture/47618911#overview">Udemy course</a>
 */
public record Card(String name,
                   String cardNumber,
                   String verificationCode,
                   String expiryDate,
                   CardType cardType
) {
}
