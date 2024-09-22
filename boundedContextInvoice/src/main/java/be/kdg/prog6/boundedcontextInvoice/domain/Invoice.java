package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Invoice {

    public Seller.CustomerUUID sellerUuid;
    private final Uom uom = Uom.T;
    private Material.MaterialUUID materialUUID;
    private LocalDate invoiceDate;
    private List<Pdt.PdtUUID> pdtUUIDList;
    public InvoiceUUID invoiceUUID;

    public record InvoiceUUID(UUID uuid){

    }

    public Invoice(Seller.CustomerUUID sellerUuid, Material.MaterialUUID materialUUID, LocalDate invoiceDate, List<Pdt.PdtUUID> pdtUUIDList,
                   InvoiceUUID invoiceUUID) {
        this.sellerUuid = sellerUuid;
        this.materialUUID = materialUUID;
        this.invoiceDate = invoiceDate;
        this.pdtUUIDList = pdtUUIDList;
        this.invoiceUUID = invoiceUUID;
    }

    public Seller.CustomerUUID getSellerUuid() {
        return sellerUuid;
    }

    public Uom getUom() {
        return uom;
    }

    public Material.MaterialUUID getMaterialUUID() {
        return materialUUID;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public List<Pdt.PdtUUID> getPdtUUIDList() {
        return pdtUUIDList;
    }

    public InvoiceUUID getInvoiceUUID() {
        return invoiceUUID;
    }
}
