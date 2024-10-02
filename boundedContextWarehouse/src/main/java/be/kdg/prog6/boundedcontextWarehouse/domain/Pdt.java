package be.kdg.prog6.boundedcontextWarehouse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pdt {

    private final LocalDateTime timeOfDelivery;
    private int amountOfTonsDelivered;

    private final PdtUUID pdtUUID;
    public record PdtUUID(UUID uuid) {

    }


    public Pdt(PdtUUID pdtUUID, LocalDateTime timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
        this.pdtUUID = pdtUUID;
    }

    public Pdt(PdtUUID pdtUUID, LocalDateTime timeOfDelivery, int amountOfTonsDelivered) {
        this.timeOfDelivery = timeOfDelivery;
        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.pdtUUID = pdtUUID;
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
}
