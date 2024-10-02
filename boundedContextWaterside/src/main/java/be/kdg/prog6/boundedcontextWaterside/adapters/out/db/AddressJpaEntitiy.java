package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class AddressJpaEntitiy {

    @Id
    @GeneratedValue
    private UUID addressUUID;

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private int houseNumber;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;

    public AddressJpaEntitiy(UUID addressUUID, String street, int houseNumber, String city, String country) {
        this.addressUUID = addressUUID;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }

    public AddressJpaEntitiy() {

    }

    public UUID getAddressUUID() {
        return addressUUID;
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
}
