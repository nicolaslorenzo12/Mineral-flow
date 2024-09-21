package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Uom;

public class OrderLine {

    private int lineNumber;
    private MaterialType materialType;
    private String description;
    private int quantity;
    private Uom uom;

    public OrderLine(int lineNumber, MaterialType materialType, String description, int quantity, Uom uom) {
        this.lineNumber = lineNumber;
        this.materialType = materialType;
        this.description = description;
        this.quantity = quantity;
        this.uom = uom;
    }
}
