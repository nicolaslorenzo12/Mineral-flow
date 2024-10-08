package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class PurchaseOrderJpaEntity {

    @Id
    private String poNumber;
    @Column(nullable = false)
    private UUID shipmentOrderUUID;

    @Column(nullable = false)
    private UUID sellerUUID;

    @Column(nullable = false)
    private UUID buyerUUID;

    @Column(nullable = false)
    private String vesselNumber;

    @Column(nullable = false)
    private LocalDate purchaseOrderDate;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<OrderLineJpaEntity> orderLines;

    public PurchaseOrderJpaEntity(String poNumber, UUID shipmentOrderUUID,UUID sellerUUID, UUID buyerUuid, String vesselNumber, LocalDate purchaseOrderDate) {
        this.poNumber = poNumber;
        this.sellerUUID = sellerUUID;
        this.buyerUUID = buyerUuid;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderDate = purchaseOrderDate;
        this.shipmentOrderUUID = shipmentOrderUUID;
    }

    public PurchaseOrderJpaEntity() {

    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public UUID getBuyerUuid() {
        return buyerUUID;
    }

    public void setBuyerUuid(UUID buyerUuid) {
        this.buyerUUID = buyerUuid;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public LocalDate getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(LocalDate purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public List<OrderLineJpaEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineJpaEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public UUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public void setShipmentOrderUUID(UUID purchaseOrderUUID) {
        this.shipmentOrderUUID = purchaseOrderUUID;
    }

    public UUID getBuyerUUID() {
        return buyerUUID;
    }

    public void setBuyerUUID(UUID buyerUUID) {
        this.buyerUUID = buyerUUID;
    }
}
