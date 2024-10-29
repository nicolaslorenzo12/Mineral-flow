package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

@Entity(name = "MaterialWarehouseEntity")
@Table(catalog = "Warehouse", name = "material-warehouse")
public class MaterialJpaEntity {

    @Id
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int storagePricePerTonPerDay;
    @Column(nullable = false)
    private int pricePerTon;

    public MaterialJpaEntity(MaterialType materialType, String description, int storagePricePerTonPerDay, int pricePerTon) {
        this.materialType = materialType;
        this.description = description;
        this.storagePricePerTonPerDay = storagePricePerTonPerDay;
        this.pricePerTon = pricePerTon;
    }

    public MaterialJpaEntity() {

    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getDescription() {
        return description;
    }

    public int getStoragePricePerTonPerDay() {
        return storagePricePerTonPerDay;
    }

    public int getPricePerTon() {
        return pricePerTon;
    }
}
