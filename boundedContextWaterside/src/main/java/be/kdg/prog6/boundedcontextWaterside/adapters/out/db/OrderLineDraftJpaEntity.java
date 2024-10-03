package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

@Entity
public class OrderLineDraftJpaEntity {

    @Id
    private int lineNumber;

    @Column(nullable = false)
    private MaterialType materialType;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String poNumber;
    @ManyToOne
    @JoinColumn(name = "poNumber",referencedColumnName = "poNumber", insertable = false, updatable = false)
    private PurchaseOrderDraftJpaEntity purchaseOrder;

    public OrderLineDraftJpaEntity(int lineNumber, MaterialType materialType, int quantity, String poNumber) {
        this.lineNumber = lineNumber;
        this.materialType = materialType;
        this.quantity = quantity;
        this.poNumber = poNumber;
    }

    public OrderLineDraftJpaEntity() {

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

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
}
