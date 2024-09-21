package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.Buyer;
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
    List<OrderLine> orderLines;

    public record PurchaseOrderUUID(UUID uuid){

    }



}
