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
    private WarehouseJpaEntity warehouse;


    public WarehouseJpaActivityEntity(UUID uuid, UUID sellerUUID, int warehouseNumber, int amountOfTons, WarehouseAction warehouseAction, UUID materialUUID) {
        this.uuid = uuid;
        this.sellerUUID = sellerUUID;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTons = amountOfTons;
        this.warehouseAction = warehouseAction;
        this.materialUUID = materialUUID;
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
}
