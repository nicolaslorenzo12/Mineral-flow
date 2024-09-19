package be.kdg.prog6.common.domain;

import java.util.UUID;

public class Customer {

    private CustomerUUID customerUUID;
    private String name;
    private Address address;

    public record CustomerUUID(UUID uuid){

    }

    public Customer(CustomerUUID customerUUID, String name, Address address) {
        this.customerUUID = customerUUID;
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }
}
