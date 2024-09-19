package be.kdg.prog6.common.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pdt {

    private Material material;
    private LocalDateTime dateOfDelivery;
    private int wareHouseNumber;
    private int dockNumber;
    private double amountDelivered;
    private Uom uom;
    public PdtUUID pdtUUID;

    public record PdtUUID(UUID uuid){

    }

    public Pdt(Material material, LocalDateTime dateOfDelivery, int wareHouseNumber, int dockNumber, double amountDelivered, Uom uom, PdtUUID pdtUUID) {
        this.material = material;
        this.dateOfDelivery = dateOfDelivery;
        this.wareHouseNumber = wareHouseNumber;
        this.dockNumber = dockNumber;
        this.amountDelivered = amountDelivered;
        this.uom = uom;
        this.pdtUUID = pdtUUID;
    }

    public Material getMaterial() {
        return material;
    }

    public LocalDateTime getDateOfDelivery() {
        return dateOfDelivery;
    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public int getDockNumber() {
        return dockNumber;
    }

    public double getAmountDelivered() {
        return amountDelivered;
    }

    public Uom getUom() {
        return uom;
    }

    public PdtUUID getPdtUUID() {
        return pdtUUID;
    }
}
