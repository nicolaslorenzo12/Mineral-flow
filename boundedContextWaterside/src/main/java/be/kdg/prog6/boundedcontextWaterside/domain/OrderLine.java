package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Uom;

public class OrderLine {

    private int lineNumber;
    private final MaterialType materialType;
    private final int quantity;
    private final Uom uom = Uom.T;

    public OrderLine(MaterialType materialType, int quantity) {
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
