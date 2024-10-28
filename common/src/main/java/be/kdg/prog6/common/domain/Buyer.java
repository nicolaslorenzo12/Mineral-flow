package be.kdg.prog6.common.domain;

public class Buyer extends Customer{
    public Buyer(CustomerUUID customerUUID, String name, Address.AddressUUID addressUUID) {
        super(customerUUID, name, addressUUID);
    }

    public Buyer(CustomerUUID customerUUID, String name) {
        super(customerUUID, name);
    }
}
