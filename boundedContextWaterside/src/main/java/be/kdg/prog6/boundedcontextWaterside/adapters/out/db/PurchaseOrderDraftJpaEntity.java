package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class PurchaseOrderDraftJpaEntity {

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
    private List<OrderLineDraftJpaEntity> orderLines;


    public PurchaseOrderDraftJpaEntity(String poNumber, UUID sellerUUID, UUID buyerUuid, String vesselNumber, LocalDate purchaseOrderDate,
                                  SellerJpaEntitty sellerJpaEntitty, BuyerJpaEntity buyerJpaEntity) {
        this.poNumber = poNumber;
        this.sellerUUID = sellerUUID;
        this.buyerUUID = buyerUuid;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderDate = purchaseOrderDate;
        this.sellerJpaEntitty = sellerJpaEntitty;
        this.buyerJpaEntity = buyerJpaEntity;
        orderLines = new ArrayList<>();
    }

    public PurchaseOrderDraftJpaEntity(String poNumber, UUID sellerUUID, UUID buyerUUID, String vesselNumber, LocalDate purchaseOrderDate) {
        this.poNumber = poNumber;
        this.sellerUUID = sellerUUID;
        this.buyerUUID = buyerUUID;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderDate = purchaseOrderDate;
        orderLines = new ArrayList<>();
    }

    public PurchaseOrderDraftJpaEntity() {

    }

    public List<OrderLineDraftJpaEntity> getOrderLines() {
        return orderLines;
    }
}
