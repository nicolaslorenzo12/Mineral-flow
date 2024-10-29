package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(catalog = "Warehouse")
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
}
