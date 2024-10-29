package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(catalog = "Warehouse")
public class OrderLineJpaEntity {

    @Id
    private UUID orderLineUUID;
    @Column(nullable = false)
    private int lineNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String poNumber;

    @ManyToOne
    @JoinColumn(name = "poNumber", insertable = false, updatable = false)
    private PurchaseOrderJpaEntity purchaseOrder;

    public OrderLineJpaEntity(UUID orderLineUUID, int lineNumber, String poNumber,MaterialType materialType, int quantity) {
        this.orderLineUUID = orderLineUUID;
        this.lineNumber = lineNumber;
        this.materialType = materialType;
        this.quantity = quantity;
        this.poNumber = poNumber;
    }

    public OrderLineJpaEntity() {

    }

    public int getLineNumber() {
        return lineNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public int getQuantity() {
        return quantity;
    }

    public UUID getOrderLineUUID() {
        return orderLineUUID;
    }
}
