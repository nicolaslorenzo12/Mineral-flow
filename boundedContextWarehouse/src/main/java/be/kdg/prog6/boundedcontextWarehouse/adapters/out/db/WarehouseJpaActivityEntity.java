package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class WarehouseJpaActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private int warehouseNumber;
    @Column(nullable = false)
    private int amountOfTons;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WarehouseAction warehouseAction;
    @ManyToOne
    @JoinColumn(name = "warehouseNumber", referencedColumnName = "warehouseNumber", insertable = false, updatable = false)
    private WarehouseJpaEntity warehouseJpaEntity;


    public WarehouseJpaActivityEntity(UUID uuid, LocalDateTime time, int warehouseNumber, int amountOfTons, WarehouseAction warehouseAction) {
        this.uuid = uuid;
        this.time = time;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTons = amountOfTons;
        this.warehouseAction = warehouseAction;
    }

    public WarehouseJpaActivityEntity(UUID uuid, LocalDateTime time, int warehouseNumber, int amountOfTons, WarehouseAction warehouseAction,
                                      WarehouseJpaEntity warehouseJpaEntity) {
        this.uuid = uuid;
        this.time = time;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTons = amountOfTons;
        this.warehouseAction = warehouseAction;
        this.warehouseJpaEntity = warehouseJpaEntity;
    }

    public WarehouseJpaActivityEntity() {

    }

    public UUID getUuid() {
        return uuid;
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
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
    public void setWarehouseJpaEntity(WarehouseJpaEntity warehouse) {
        this.warehouseJpaEntity = warehouse;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public WarehouseJpaEntity getWarehouseJpaEntity() {
        return warehouseJpaEntity;
    }
}
