package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "seller-waterside")
public class SellerJpaEntitty {

    @Id
    @GeneratedValue
    private UUID sellerUUID;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressUUID", referencedColumnName = "addressUUID")
    private AddressJpaEntitiy address;

    public SellerJpaEntitty(UUID sellerUUID, String name, AddressJpaEntitiy address) {
        this.sellerUUID = sellerUUID;
        this.name = name;
        this.address = address;
    }

    public SellerJpaEntitty() {

    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
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
