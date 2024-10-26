package be.kdg.prog6.common.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Storage {

    private int warehouseNumber;
    private LocalDateTime timeOfDelivery;
    private int amountOfTonsDelivered;
    private int amountOfTonsConsumed;
    private boolean allTonsConsumed;

    private PdtUUID pdtUUID;

    public record PdtUUID(UUID uuid) {

    }

    public Storage(int warehouseNumber, LocalDateTime timeOfDelivery, int amountOfTonsDelivered, PdtUUID pdtUUID, int amountOfTonsConsumed, boolean allTonsConsumed) {
        this.warehouseNumber = warehouseNumber;
        this.timeOfDelivery = timeOfDelivery;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.pdtUUID = pdtUUID;
        this.amountOfTonsConsumed = amountOfTonsConsumed;
        this.allTonsConsumed = allTonsConsumed;
    }


    public Storage(int amountOfTonsDelivered, LocalDateTime timeOfDelivery) {

        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.timeOfDelivery = timeOfDelivery;
    }

    public Storage() {
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

    public int removeTonsFromPdt(int amountOfTonsToDispatch){

        int balanceOfAmountOfTonsToDispatch;
        int availableAmountOfTonsToDispatch = this.getAmountOfTonsDelivered() - this.getAmountOfTonsConsumed();

        if(amountOfTonsToDispatch < availableAmountOfTonsToDispatch){
            this.setAmountOfTonsConsumed(amountOfTonsToDispatch + this.getAmountOfTonsConsumed());
            balanceOfAmountOfTonsToDispatch = 0;
        }
        else{
            balanceOfAmountOfTonsToDispatch = amountOfTonsToDispatch - availableAmountOfTonsToDispatch;
            this.setAmountOfTonsConsumed(this.getAmountOfTonsDelivered());
            this.setAllTonsConsumed(true);
        }

        return balanceOfAmountOfTonsToDispatch;
    }
}