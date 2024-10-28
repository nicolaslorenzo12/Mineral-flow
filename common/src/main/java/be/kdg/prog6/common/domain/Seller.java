package be.kdg.prog6.common.domain;

public class Seller extends Customer{
    public Seller(CustomerUUID customerUUID, String name, Address.AddressUUID addressUUID) {
        super(customerUUID, name, addressUUID);
    }

    public Seller(CustomerUUID customerUUID, String name) {
        super(customerUUID, name);
    }



}

