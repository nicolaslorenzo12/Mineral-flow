package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PurchaseOrder {

    private LocalDateTime date;
    private Seller.CustomerUUID sellerUuid;
    private Buyer.CustomerUUID buyerUuid;
    private String vesselNumber;
    private PurchaseOrderUUID purchaseOrderUUID;
    List<OrderLine> orderLineUUIDS;

    public record PurchaseOrderUUID(UUID uuid){

    }

    public PurchaseOrder(LocalDateTime date, Seller.CustomerUUID sellerUuid, Buyer.CustomerUUID buyerUuid, String vesselNumber,
                         PurchaseOrderUUID purchaseOrderUUID, List<OrderLine> orderLineUUIDS) {
        this.date = date;
        this.sellerUuid = sellerUuid;
        this.buyerUuid = buyerUuid;
        this.vesselNumber = vesselNumber;
        this.purchaseOrderUUID = purchaseOrderUUID;
        this.orderLineUUIDS = orderLineUUIDS;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Seller.CustomerUUID getSellerUuid() {
        return sellerUuid;
    }

    public Buyer.CustomerUUID getBuyerUuid() {
        return buyerUuid;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public PurchaseOrderUUID getPurchaseOrderUUID() {
        return purchaseOrderUUID;
    }

    public List<OrderLine> getOrderLineUUIDS() {
        return orderLineUUIDS;
    }
}
