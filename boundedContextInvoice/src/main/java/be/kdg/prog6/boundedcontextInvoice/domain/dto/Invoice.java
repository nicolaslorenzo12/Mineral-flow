package be.kdg.prog6.boundedcontextInvoice.domain.dto;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceMaterialAndAmountToPayForMaterial;

import java.util.List;

public class Invoice {

    private final String sellerName;
    private final List<InvoiceMaterialAndAmountToPayForMaterial> invoiceMaterialAndAmountToPayForMaterial;
    private final int totalAmountToPay;

    public Invoice(String sellerName, List<InvoiceMaterialAndAmountToPayForMaterial> invoiceMaterialAndAmountToPayForMaterial, int totalAmountToPay) {
        this.sellerName = sellerName;
        this.invoiceMaterialAndAmountToPayForMaterial = invoiceMaterialAndAmountToPayForMaterial;
        this.totalAmountToPay = totalAmountToPay;
    }

    public String getSellerName() {
        return sellerName;
    }

    public List<InvoiceMaterialAndAmountToPayForMaterial> getInvoiceMaterialAndAmountToPayForMaterial() {
        return invoiceMaterialAndAmountToPayForMaterial;
    }

    public int getTotalAmountToPay() {
        return totalAmountToPay;
    }
}
