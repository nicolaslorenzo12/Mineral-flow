package be.kdg.prog6.common.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pdt {

    private LocalDateTime timeOfLoad;
    private int wareHouseNumber;
    private int dockNumber;
    private final Uom uom = Uom.T;
    private int amountDelivered;
    public PdtUUID pdtUUID;

    public record PdtUUID(UUID uuid){

    }

    public Pdt(LocalDateTime timeOfLoad, int wareHouseNumber, int dockNumber, int amountDelivered, PdtUUID pdtUUID) {
        this.timeOfLoad = timeOfLoad;
        this.wareHouseNumber = wareHouseNumber;
        this.dockNumber = dockNumber;
        this.amountDelivered = amountDelivered;
        this.pdtUUID = pdtUUID;
    }

    public LocalDateTime getTimeOfLoad() {
        return timeOfLoad;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public int getDockNumber() {
        return dockNumber;
    }

    public Uom getUom() {
        return uom;
    }

    public int getAmountDelivered() {
        return amountDelivered;
    }

    public PdtUUID getPdtUUID() {
        return pdtUUID;
    }
}
