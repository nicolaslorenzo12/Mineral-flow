package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.ports.out.CreatePurchaseOrderDraftPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Component
public class PurchaseOrderDraftDBAdapter implements CreatePurchaseOrderDraftPort {

    private final PurchaseOrderDraftJpaEntityRepository purchaseOrderDraftJpaEntityRepository;

    public PurchaseOrderDraftDBAdapter(PurchaseOrderDraftJpaEntityRepository purchaseOrderDraftJpaEntityRepository) {
        this.purchaseOrderDraftJpaEntityRepository = purchaseOrderDraftJpaEntityRepository;
    }

    @Override
    public void createPurchaseOrderDraft(UUID sellerUUID, UUID buyerUUID, String vesselNumber, LocalDate purchaseOrderDate) {

        Random random = new Random();
        int randomDigits = 100000 + random.nextInt(900000);
        String poNumber = "PO" + randomDigits;
        PurchaseOrderDraftJpaEntity purchaseOrderDraftJpaEntity = new PurchaseOrderDraftJpaEntity(poNumber, sellerUUID, buyerUUID, vesselNumber, purchaseOrderDate);
        purchaseOrderDraftJpaEntityRepository.save(purchaseOrderDraftJpaEntity);
    }
}
