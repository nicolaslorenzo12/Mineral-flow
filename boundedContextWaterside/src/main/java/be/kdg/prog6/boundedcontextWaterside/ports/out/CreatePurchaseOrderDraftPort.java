package be.kdg.prog6.boundedcontextWaterside.ports.out;

import java.time.LocalDate;
import java.util.UUID;

public interface CreatePurchaseOrderDraftPort {

    void createPurchaseOrderDraft(UUID sellerUUID, UUID buyerUUID, String vesselNumber, LocalDate purchaseOrderDate);
}
