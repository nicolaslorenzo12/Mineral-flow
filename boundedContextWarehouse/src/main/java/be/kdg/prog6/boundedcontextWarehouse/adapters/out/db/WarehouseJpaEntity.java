package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity
public class WarehouseJpaEntity {

    @Id
    private int warehouseNumber;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID sellerUUID;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID materialUUID;

    @OneToMany(mappedBy = "warehouseNumber", cascade = CascadeType.ALL)
    private List<WarehouseJpaActivityEntity> activities;


    public WarehouseJpaEntity(int wareHouseNumber, UUID sellerUUID, UUID materialUUID) {
        this.warehouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialUUID = materialUUID;
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

    public UUID getMaterialUUID() {
        return materialUUID;
    }

    public void setMaterialUUID(UUID materialUUID) {
        this.materialUUID = materialUUID;
    }

    public List<WarehouseJpaActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<WarehouseJpaActivityEntity> activities) {
        this.activities = activities;
    }
}
