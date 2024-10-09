package be.kdg.prog6.boundedcontextWarehouse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pdt {

    private int warehouseNumber;
    private LocalDateTime timeOfDelivery;
    private int amountOfTonsDelivered;

    private PdtUUID pdtUUID;
    public record PdtUUID(UUID uuid) {

    }

    public Pdt(int warehouseNumber, LocalDateTime timeOfDelivery, int amountOfTonsDelivered, PdtUUID pdtUUID) {
        this.warehouseNumber = warehouseNumber;
        this.timeOfDelivery = timeOfDelivery;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.pdtUUID = pdtUUID;
    }

    public Pdt() {
    }

    public LocalDateTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public int getAmountOfTonsDelivered() {
        return amountOfTonsDelivered;
    }

    public void setAmountOfTonsDelivered(int amountOfTonsDelivered) {
        this.amountOfTonsDelivered = amountOfTonsDelivered;
    }

    public PdtUUID getPdtUUID() {
        return pdtUUID;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }
}