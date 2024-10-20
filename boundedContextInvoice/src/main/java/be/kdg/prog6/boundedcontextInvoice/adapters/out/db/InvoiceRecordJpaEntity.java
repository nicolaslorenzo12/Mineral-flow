package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(catalog="Invoice")
public class InvoiceRecordJpaEntity {

    @Id
    @GeneratedValue
    private UUID invoiceUUID;
    @Column(nullable = false)
    public UUID sellerUUID;
    @Column(nullable = false)
    private int amountOfTons;
    @Column(nullable = false)
    private LocalDate pdtCreationDate;
    @Column(nullable = false)
    private LocalDate invoiceDate;
    @Column(nullable = false)
    private MaterialType materialType;

    public InvoiceRecordJpaEntity(UUID invoiceUUID, UUID sellerUUID, LocalDate pdtCreationDate,
                                  int amountOfTons, LocalDate invoiceDate, MaterialType materialType) {
        this.invoiceUUID = invoiceUUID;
        this.sellerUUID = sellerUUID;
        this.pdtCreationDate = pdtCreationDate;
        this.amountOfTons = amountOfTons;
        this.invoiceDate = invoiceDate;
        this.materialType = materialType;
    }

    public InvoiceRecordJpaEntity() {

    }

    public UUID getInvoiceUUID() {
        return invoiceUUID;
    }

    public void setInvoiceUUID(UUID invoiceUUID) {
        this.invoiceUUID = invoiceUUID;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public int getAmountOfTons() {
        return amountOfTons;
    }

    public void setAmountOfTons(int amountOfTons) {
        this.amountOfTons = amountOfTons;
    }

    public LocalDate getPdtCreationDate() {
        return pdtCreationDate;
    }

    public void setPdtCreationDate(LocalDate pdtCreationDate) {
        this.pdtCreationDate = pdtCreationDate;
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
}
