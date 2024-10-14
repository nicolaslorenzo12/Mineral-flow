package be.kdg.prog6.common.domain;

import java.util.UUID;

public class OrderLine {

    private int lineNumber;
    private MaterialType materialType;
    private int quantity;
    private Uom uom = Uom.T;

    private OrderLineUUID orderLineUUID;

    public record OrderLineUUID(UUID uuid){

    }


    public OrderLine(){

    }
    public OrderLine(MaterialType materialType, int quantity) {
        this.materialType = materialType;
        this.quantity = quantity;
    }

    public OrderLine(OrderLineUUID orderLineUUID, int lineNumber,MaterialType materialType, int quantity) {
        this.orderLineUUID = orderLineUUID;
        this.lineNumber = lineNumber;
        this.materialType = materialType;
        this.quantity = quantity;
    }

    public int getLineNumber() {
        return lineNumber;
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
}
