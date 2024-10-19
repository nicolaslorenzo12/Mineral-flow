package be.kdg.prog6.boundedcontextWarehouse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pdt {

    private int warehouseNumber;
    private LocalDateTime timeOfDelivery;
    private int amountOfTonsDelivered;
    private int amountOfTonsConsumed;
    private boolean allTonsConsumed;

    private PdtUUID pdtUUID;

    public record PdtUUID(UUID uuid) {

    }

    public Pdt(int warehouseNumber, LocalDateTime timeOfDelivery, int amountOfTonsDelivered, PdtUUID pdtUUID, int amountOfTonsConsumed, boolean allTonsConsumed) {
        this.warehouseNumber = warehouseNumber;
        this.timeOfDelivery = timeOfDelivery;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.pdtUUID = pdtUUID;
        this.amountOfTonsConsumed = amountOfTonsConsumed;
        this.allTonsConsumed = allTonsConsumed;
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

    public int removeTonsFromPdt(int amountNeeded){

        int balanceOfAmountOfTonsToDispatch;

        if(amountNeeded < this.getAmountOfTonsDelivered()){
            this.setAmountOfTonsConsumed(amountNeeded);
            balanceOfAmountOfTonsToDispatch = 0;
        }
        else{
            this.setAmountOfTonsConsumed(this.getAmountOfTonsDelivered());
            this.setAllTonsConsumed(true);
            balanceOfAmountOfTonsToDispatch = amountNeeded - this.getAmountOfTonsDelivered();
        }

        return balanceOfAmountOfTonsToDispatch;
    }
}