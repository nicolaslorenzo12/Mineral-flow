package be.kdg.prog6.common.domain;

public class Buyer extends Customer{
    public Buyer(CustomerUUID customerUUID, String name, Address address) {
        super(customerUUID, name, address);
    }

    public Buyer(CustomerUUID customerUUID, String name) {
        super(customerUUID, name);
    }
}
