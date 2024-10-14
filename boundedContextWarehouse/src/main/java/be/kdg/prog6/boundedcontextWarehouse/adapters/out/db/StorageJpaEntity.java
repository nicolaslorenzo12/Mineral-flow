package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "Warehouse")
public class StorageJpaEntity {

    @Id
    private UUID pdtUUID;
    @Column(nullable = false)
    private LocalDateTime timeOfDelivery;
    @Column(nullable = false)
    private int warehouseNumber;
    private int amountOfTonsDelivered;
    @Column(nullable = false)
    private int amountOfTonsConsumed;
    @Column(nullable = false)
    private boolean allTonsConsumed;
    @ManyToOne
    @JoinColumn(name = "warehouseNumber", insertable = false, updatable = false)
    private WarehouseJpaEntity warehouseJpaEntity;
    public StorageJpaEntity(UUID pdtUUID, LocalDateTime timeOfDelivery, int warehouseNumber, int amountOfTonsDelivered,
                            int amountOfTonsConsumed, boolean allTonsConsumed) {
        this.pdtUUID = pdtUUID;
        this.timeOfDelivery = timeOfDelivery;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.amountOfTonsConsumed = amountOfTonsConsumed;
        this.allTonsConsumed = allTonsConsumed;
    }

    public StorageJpaEntity() {

    }

    public UUID getPdtUUID() {
        return pdtUUID;
    }

    public void setPdtUUID(UUID pdtUUID) {
        this.pdtUUID = pdtUUID;
    }

    public LocalDateTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(LocalDateTime timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public int getAmountOfTonsDelivered() {
        return amountOfTonsDelivered;
    }

    public void setAmountOfTonsDelivered(int amountOfTonsDelivered) {
        this.amountOfTonsDelivered = amountOfTonsDelivered;
    }

    public int getAmountOfTonsConsumed() {
        return amountOfTonsConsumed;
    }

    public void setAmountOfTonsConsumed(int amountOfTonsConsumed) {
        this.amountOfTonsConsumed = amountOfTonsConsumed;
    }

    public boolean isAllTonsConsumed() {
        return allTonsConsumed;
    }

    public void setAllTonsConsumed(boolean allTonsConsumed) {
        this.allTonsConsumed = allTonsConsumed;
    }
}
