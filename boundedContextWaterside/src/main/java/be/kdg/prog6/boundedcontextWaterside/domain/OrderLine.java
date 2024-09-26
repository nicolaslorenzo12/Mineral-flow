package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Uom;
import java.util.UUID;

public class OrderLine {

    private MaterialType materialType;
    private int quantity;
    private Uom uom;
    private OrderLineUUID lineUUID;

    public record OrderLineUUID(UUID uuid){

    }

    public OrderLine(MaterialType materialType , int quantity, Uom uom, OrderLineUUID lineUUID) {
        this.materialType = materialType;
        this.quantity = quantity;
        this.uom = uom;
        this.lineUUID = lineUUID;
    }

    public MaterialType getMaterialType() {
        return materialType;
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
