package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "seller-landside")
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
    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
