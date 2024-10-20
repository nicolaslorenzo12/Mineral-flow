package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "warehouse-invoice")
@Table(catalog = "Invoice", name = "warehouse-invoice", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sellerUUID", "materialType"})
})
public class WarehouseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseNumber;

    @Column(nullable = false)
    private UUID sellerUUID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    public WarehouseJpaEntity(int warehouseNumber, UUID sellerUUID, MaterialType materialType) {
        this.warehouseNumber = warehouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
    }


    public WarehouseJpaEntity() {

    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
