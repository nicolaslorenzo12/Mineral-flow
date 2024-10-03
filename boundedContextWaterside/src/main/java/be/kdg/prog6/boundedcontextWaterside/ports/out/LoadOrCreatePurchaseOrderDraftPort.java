package be.kdg.prog6.boundedcontextWaterside.ports.out;

import be.kdg.prog6.boundedcontextWaterside.domain.OrderLine;
import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDate;
import java.util.UUID;

public interface LoadOrCreatePurchaseOrderDraftPort {

    void createPurchaseOrderDraft(UUID sellerUUID, UUID buyerUUID, String vesselNumber, LocalDate purchaseOrderDate);
    void createOrderLineDraft(String poNumber, OrderLine orderLine);
}
