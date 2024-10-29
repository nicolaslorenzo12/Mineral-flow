package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.util.UUID;

public class InvoiceRecord {

    private Seller.CustomerUUID sellerUUID;
    private int amountOfTons;
    private LocalDate pdtCreationDate;
    private LocalDate invoiceDate;
    private MaterialType materialType;
    private InvoiceUUID invoiceUUID;

    public record InvoiceUUID(UUID uuid){

    }

    public InvoiceRecord(Seller.CustomerUUID sellerUUID, int amountOfTons, LocalDate pdtCreationDate, LocalDate invoiceDate, MaterialType materialType,
                         InvoiceUUID invoiceUUID) {
        this.sellerUUID = sellerUUID;
        this.amountOfTons = amountOfTons;
        this.pdtCreationDate = pdtCreationDate;
        this.invoiceDate = invoiceDate;
        this.materialType = materialType;
        this.invoiceUUID = invoiceUUID;
    }

    public InvoiceRecord(InvoiceUUID invoiceUUID) {
    }


    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(Seller.CustomerUUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public int getAmountOfTons() {
        return amountOfTons;
    }

    public LocalDate getPdtCreationDate() {
        return pdtCreationDate;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public InvoiceUUID getInvoiceUUID() {
        return invoiceUUID;
    }
}
