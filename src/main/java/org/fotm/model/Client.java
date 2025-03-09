package org.fotm.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Record implements interface.
 */
public record Client(long id, String firstName, String lastName, List<Contact> contacts, Contact.Address primaryAddress,
                     BigDecimal accountBalance)
    implements AccountHolder {

    public static BigDecimal DEFAULT_BALANCE = BigDecimal.ZERO;
    // From Baeldung
    static String countryCode;

    static {
        countryCode = "US";
    }

    public static String getCountryCode() {
        return countryCode;
    }

    @Override
    public String fullName() {
        return String.join(" ", firstName, lastName);
    }


    public enum ContactType {
        PRIMARY,
        EMERGENCY,
        ALTERNATE2,
        ALTERNATE3
    }

    /**
     * Nested record.
     */
    public record Contact(long id, String phone, String email, ContactType contactType, Address address) {
        /**
         * Nested record in nested record.
         */
        public record Address(long id, String street1, String street2, String city, String state, String postalCode) {

            public Address() {
                this(0L, "", "", "", "", "");
            }
        }
    }

}
