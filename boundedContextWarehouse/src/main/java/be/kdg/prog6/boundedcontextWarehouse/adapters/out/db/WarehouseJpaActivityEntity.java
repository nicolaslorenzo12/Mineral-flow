package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
public class WarehouseJpaActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID sellerUUID;

    private int warehouseNumber;

    private int amountOfTons;

    @Enumerated(EnumType.STRING)
    private WarehouseAction warehouseAction;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID materialUUID;

    @ManyToOne
    @JoinColumn(name = "warehouseNumber", referencedColumnName = "warehouseNumber", insertable = false, updatable = false)
    private WarehouseJpaEntity warehouseJpaEntity;


    public WarehouseJpaActivityEntity(UUID uuid, UUID sellerUUID, int warehouseNumber, int amountOfTons, WarehouseAction warehouseAction, UUID materialUUID) {
        this.uuid = uuid;
        this.sellerUUID = sellerUUID;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTons = amountOfTons;
        this.warehouseAction = warehouseAction;
        this.materialUUID = materialUUID;
    }

    public WarehouseJpaActivityEntity(UUID sellerUUID, int warehouseNumber, int amountOfTons, WarehouseAction warehouseAction, UUID materialUUID,
                                      WarehouseJpaEntity warehouseJpaEntity) {
        this.sellerUUID = sellerUUID;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTons = amountOfTons;
        this.warehouseAction = warehouseAction;
        this.materialUUID = materialUUID;
        this.warehouseJpaEntity = warehouseJpaEntity;
    }

    public WarehouseJpaActivityEntity() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public int getAmountOfTons() {
        return amountOfTons;
    }

    public WarehouseAction getWarehouseAction() {
        return warehouseAction;
    }

    public UUID getMaterialUUID() {
        return materialUUID;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public void setAmountOfTons(int amountOfTons) {
        this.amountOfTons = amountOfTons;
    }

    public void setWarehouseAction(WarehouseAction warehouseAction) {
        this.warehouseAction = warehouseAction;
    }

    public void setMaterialUUID(UUID materialUUID) {
        this.materialUUID = materialUUID;
    }

    public void setWarehouseJpaEntity(WarehouseJpaEntity warehouse) {
        this.warehouseJpaEntity = warehouse;
    }
}
