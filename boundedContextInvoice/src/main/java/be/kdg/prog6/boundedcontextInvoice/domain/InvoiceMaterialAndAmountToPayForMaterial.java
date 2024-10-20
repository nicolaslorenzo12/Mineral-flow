package be.kdg.prog6.boundedcontextInvoice.domain;

public class InvoiceMaterialAndAmountToPayForMaterial {

    private final String materialDescription;
    private final int amountToPayForMaterial;

    public InvoiceMaterialAndAmountToPayForMaterial(String materialDescription, int amountToPayForMaterial) {
        this.materialDescription = materialDescription;
        this.amountToPayForMaterial = amountToPayForMaterial;
    }

    public int getAmountToPayForMaterial() {
        return amountToPayForMaterial;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }
}
