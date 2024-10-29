package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(catalog = "Landside", name = "seller-landside")
public class SellerJpaEntity {

    @Id
    @GeneratedValue
    private UUID sellerUUID;

    @Column(nullable = false)
    private String name;

    public SellerJpaEntity(UUID sellerUUID, String name) {
        this.sellerUUID = sellerUUID;
        this.name = name;
    }

    public SellerJpaEntity() {

    }
    public UUID getSellerUUID() {
        return sellerUUID;
    }
    public String getName() {
        return name;
    }
}
