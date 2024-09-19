package be.kdg.prog6.common.domain;

public class Seller extends Customer{
    public Seller(CustomerUUID customerUUID, String name, Address address) {
        super(customerUUID, name, address);
    }

    public Seller(CustomerUUID customerUUID, String name) {
        super(customerUUID, name);
    }
}
