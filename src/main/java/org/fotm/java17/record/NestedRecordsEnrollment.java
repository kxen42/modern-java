package org.fotm.java17.record;

import org.fotm.model.AccountHolder;
import org.fotm.model.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class NestedRecordsEnrollment {
    public static void main(String[] args) {

        List<Client> clients = new ArrayList<>();

        Client c1 = getClient();
        clients.add(c1);
        clients.add(getClient(42L, "Wilma", "Flintstone", BigDecimal.valueOf(100)));
        clients.add(getClient(52L, "Betty", "Rubble", BigDecimal.valueOf(1_000)));
        clients.add(getClient(62L, "Barney", "Rubble", BigDecimal.valueOf(-200)));

        System.out.println("instance method in record" + c1.fullName());


        BigDecimal totalDeposits = clients.stream()
                                          .map(AccountHolder::accountBalance)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("using interface of record Total Deposits: " + totalDeposits);
        System.out.println("Static method & initializer in record countryCode " + Client.getCountryCode());
        System.out.println("Static static member in record DEFAULT_BALANCE " + Client.DEFAULT_BALANCE);
        System.out.println("record with 2 levels of nested records" + c1);

        System.out.println("using local record in method for streaming");
        useLocalRecord(clients);

    }

    private static Client getClient() {
        Client.Contact.Address cca = new Client.Contact.Address(30L, "123 Sesame Street", "", "New York", "NY", "12345");
        List<Client.Contact> c1Contacts = new ArrayList<>();
        Client.Contact c1Contact1 = new Client.Contact(1L, "555-507-1254", null, Client.ContactType.ALTERNATE2, new Client.Contact.Address());
        Client.Contact c1Contact2 = new Client.Contact(2L, "555-509-6688", "fflintstone@aol.com", Client.ContactType.PRIMARY, cca);
        c1Contacts.add(c1Contact1);
        c1Contacts.add(c1Contact2);

        Client.Contact.Address c1Address = new Client.Contact.Address(1L, "42 Stone Lane", null, "Bedrock", "CA", "90210");
        return new Client(1L, "Fred", "Flintstone", c1Contacts, c1Address, BigDecimal.ONE);
    }

    private static Client getClient(long id, String firstName, String lastName, BigDecimal balance) {
        List<Client.Contact> contacts = new ArrayList<>();
        Client.Contact contact = new Client.Contact(1L, "555-507-1254", null, Client.ContactType.ALTERNATE2, new Client.Contact.Address());
        contacts.add(contact);
        Client.Contact.Address address = new Client.Contact.Address();
        return new Client(id, firstName, lastName, contacts, address, balance);
    }

    private static void useLocalRecord(List<Client> clients) {
        record Name(String fullName) {
        }
        Stream<Name> nameStream = clients.stream()
                                         .map(c -> new Name(c.fullName()));
        nameStream.sorted(Comparator.comparing(Name::fullName))
                  .forEach(System.out::println);
    }
}
