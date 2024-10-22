package be.kdg.prog6.boundedcontextInvoice.adapters.in.web.dto;


public class InvoiceLineDto {

    private final String materialDescription;
    private final int amountToPayForMaterial;

    public InvoiceLineDto(String materialDescription, int amountToPayForMaterial) {
        this.materialDescription = materialDescription;
        this.amountToPayForMaterial = amountToPayForMaterial;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public int getAmountToPayForMaterial() {
        return amountToPayForMaterial;
    }
}
