package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class BuyerJpaEntity {

    @Id
    @GeneratedValue
    private UUID buyerUUID;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressUUID", referencedColumnName = "addressUUID")
    private AddressJpaEntitiy address;

    public BuyerJpaEntity(UUID buyerUUID, String name, AddressJpaEntitiy address) {
        this.buyerUUID = buyerUUID;
        this.name = name;
        this.address = address;
    }

    public BuyerJpaEntity() {

    }

    public UUID getBuyerUUID() {
        return buyerUUID;
    }

    public void setBuyerUUID(UUID buyerUUID) {
        this.buyerUUID = buyerUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressJpaEntitiy getAddress() {
        return address;
    }

    public void setAddress(AddressJpaEntitiy address) {
        this.address = address;
    }
}
