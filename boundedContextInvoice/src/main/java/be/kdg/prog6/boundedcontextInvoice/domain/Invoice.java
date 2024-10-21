package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.Seller;

import java.util.List;

public class Invoice {

    private final Seller.CustomerUUID sellerUUID;
    private final List<InvoiceLine> invoiceLines;
    private final int totalAmountToPay;

    public Invoice(Seller.CustomerUUID sellerUUID, List<InvoiceLine> invoiceMaterialAndAmountToPayForMaterial, int totalAmountToPay) {
        this.sellerUUID = sellerUUID;
        this.invoiceLines = invoiceMaterialAndAmountToPayForMaterial;
        this.totalAmountToPay = totalAmountToPay;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public int getTotalAmountToPay() {
        return totalAmountToPay;
    }
}
