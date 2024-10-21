package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.MaterialType;

public class InvoiceLine {

    private final MaterialType materialType;
    private final int amountToPayForMaterial;

    public InvoiceLine(MaterialType materialType, int amountToPayForMaterial) {
        this.materialType = materialType;
        this.amountToPayForMaterial = amountToPayForMaterial;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public int getAmountToPayForMaterial() {
        return amountToPayForMaterial;
    }
}
