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

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getOrderLineUUID() {
        return orderLineUUID;
    }

    public void setOrderLineUUID(UUID orderLineUUID) {
        this.orderLineUUID = orderLineUUID;
    }
}
