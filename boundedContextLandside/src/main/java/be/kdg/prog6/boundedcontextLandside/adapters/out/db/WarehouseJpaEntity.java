package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "landside")
@Table(catalog = "Landside", name = "warehouse-landside", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sellerUUID", "materialType"})
})
public class WarehouseJpaEntity {
    @Id
    private int wareHouseNumber;
    @Column(nullable = false)
    private UUID sellerUUID;
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    private int utilizationCapacity = 0;

    public WarehouseJpaEntity(int wareHouseNumber, UUID sellerUUID, MaterialType materialType, int utilizationCapacity) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialType = materialType;
        this.utilizationCapacity = utilizationCapacity;
    }

    public WarehouseJpaEntity() {

    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public void setWareHouseNumber(int wareHouseNumber) {
        this.wareHouseNumber = wareHouseNumber;
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

    public int getUtilizationCapacity() {
        return utilizationCapacity;
    }

    public void setUtilizationCapacity(int utilizationCapacity) {
        this.utilizationCapacity = utilizationCapacity;
    }
}
