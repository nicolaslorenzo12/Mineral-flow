package be.kdg.prog6.common.domain;

import java.util.Objects;
import java.util.UUID;

public class Address {

    private final String street;
    private final int houseNumber;
    private final String city;
    private final String country;
    private final AddressUUID addressUUID;

    public record AddressUUID(UUID uuid) {

    }

    public Address(String street, int houseNumber, String city, String country, AddressUUID addressUUID) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.addressUUID = addressUUID;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public AddressUUID getAddressUUID() {
        return addressUUID;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return getHouseNumber() == address.getHouseNumber() && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getCountry(), address.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getHouseNumber(), getCity(), getCountry());
    }
}
