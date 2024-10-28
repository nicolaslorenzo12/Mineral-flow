package be.kdg.prog6.common.domain;

import java.util.UUID;
public class Customer {

    private final CustomerUUID customerUUID;
    private final String name;
    private Address.AddressUUID addressUUID;

    public record CustomerUUID(UUID uuid){

    }

    public Customer(CustomerUUID customerUUID, String name, Address.AddressUUID addressUUID) {
        this.customerUUID = customerUUID;
        this.name = name;
        this.addressUUID = addressUUID;
    }

    public Customer(CustomerUUID customerUUID, String name) {
        this.customerUUID = customerUUID;
        this.name = name;
    }

    public CustomerUUID getCustomerUUID() {
        return customerUUID;
    }

    public String getName() {
        return name;
    }

    public Address.AddressUUID getAddress() {
        return addressUUID;
    }
}
