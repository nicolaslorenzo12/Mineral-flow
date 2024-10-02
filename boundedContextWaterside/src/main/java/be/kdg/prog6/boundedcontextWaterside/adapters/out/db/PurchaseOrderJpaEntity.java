package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class PurchaseOrderJpaEntity {

    @Id
    private String poNumber;

    @Column(nullable = false)
    private UUID sellerUUID;

    @Column(nullable = false)
    private UUID buyerUUID;

    @Column(nullable = false)
    private String vesselNumber;

    @Column(nullable = false)
    private LocalDate purchaseOrderDate;

    @OneToOne
    @JoinColumn(name = "sellerUUID", referencedColumnName = "sellerUUID", insertable = false, updatable = false)
    private SellerJpaEntitty sellerJpaEntitty;

    @OneToOne
    @JoinColumn(name = "buyerUUID", referencedColumnName = "buyerUUID", insertable = false, updatable = false)
    private BuyerJpaEntity buyerJpaEntity;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<OrderLineJpaEntity> orderLines;

    public PurchaseOrderJpaEntity(String poNumber, UUID sellerUUID, UUID buyerUuid, String vesselNumber, LocalDate purchaseOrderDate,
                                  SellerJpaEntitty sellerJpaEntitty, BuyerJpaEntity buyerJpaEntity) {
        this.poNumber = poNumber;
        this.sellerUUID = sellerUUID;
        this.buyerUUID = buyerUuid;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderDate = purchaseOrderDate;
        this.sellerJpaEntitty = sellerJpaEntitty;
        this.buyerJpaEntity = buyerJpaEntity;
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

    public SellerJpaEntitty getSellerJpaEntitty() {
        return sellerJpaEntitty;
    }

    public void setSellerJpaEntitty(SellerJpaEntitty sellerJpaEntitty) {
        this.sellerJpaEntitty = sellerJpaEntitty;
    }

    public BuyerJpaEntity getBuyerJpaEntity() {
        return buyerJpaEntity;
    }

    public void setBuyerJpaEntity(BuyerJpaEntity buyerJpaEntity) {
        this.buyerJpaEntity = buyerJpaEntity;
    }

    public List<OrderLineJpaEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineJpaEntity> orderLines) {
        this.orderLines = orderLines;
    }
}
