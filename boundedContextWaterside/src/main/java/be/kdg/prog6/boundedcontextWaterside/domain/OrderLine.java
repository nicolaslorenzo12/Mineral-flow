package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Uom;
import java.util.UUID;

public class OrderLine {

    private Material.MaterialUUID materialUUID;
    private int quantity;
    private Uom uom;
    private OrderLineUUID lineUUID;

    public record OrderLineUUID(UUID uuid){

    }

    public OrderLine(Material.MaterialUUID materialUUID, int quantity, Uom uom, OrderLineUUID lineUUID) {
        this.materialUUID = materialUUID;
        this.quantity = quantity;
        this.uom = uom;
        this.lineUUID = lineUUID;
    }

    public Material.MaterialUUID getMaterialUUID() {
        return materialUUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Uom getUom() {
        return uom;
    }

    public OrderLineUUID getLineUUID() {
        return lineUUID;
    }
}
