package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity
public class WarehouseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseNumber;

    @Column(nullable = false)
    private UUID sellerUUID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @OneToMany(mappedBy = "warehouseJpaEntity", cascade = CascadeType.ALL)
    private List<WarehouseJpaActivityEntity> activities;


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

    public void setWarehouseNumber(int wareHouseNumber) {
        this.warehouseNumber = wareHouseNumber;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public List<WarehouseJpaActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<WarehouseJpaActivityEntity> activities) {
        this.activities = activities;
    }

    public void addActivity(WarehouseJpaActivityEntity warehouseJpaActivityEntity){
        activities.add(warehouseJpaActivityEntity);
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
