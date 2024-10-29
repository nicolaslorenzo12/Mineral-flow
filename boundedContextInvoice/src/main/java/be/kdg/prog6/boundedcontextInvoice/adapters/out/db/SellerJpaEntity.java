package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name ="SellerInvoiceEntity")
@Table(catalog = "Invoice", name = "seller-invoice")
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

}
