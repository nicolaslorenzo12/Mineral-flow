package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "Warehouse")
public class PdtJpaEntity {

    @Id
    private UUID pdtUUID;
    @Column(nullable = false)
    private LocalDateTime timeOfDelivery;
    @Column(nullable = false)
    private int warehouseNumber;
    private int amountOfTonsDelivered;
    @ManyToOne
    @JoinColumn(name = "warehouseNumber", insertable = false, updatable = false)
    private WarehouseJpaEntity warehouseJpaEntity;
    public PdtJpaEntity(UUID pdtUUID, LocalDateTime timeOfDelivery, int warehouseNumber, int amountOfTonsDelivered) {
        this.pdtUUID = pdtUUID;
        this.timeOfDelivery = timeOfDelivery;
        this.warehouseNumber = warehouseNumber;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
    }

    public PdtJpaEntity() {

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
}
