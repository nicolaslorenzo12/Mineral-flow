package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.domain.OrderLine;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreatePurchaseOrderDraftPort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Component
public class PurchaseOrderDraftDBAdapter implements LoadOrCreatePurchaseOrderDraftPort {

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

    @Override
    public void createOrderLineDraft(String poNumber, OrderLine orderLine) {

        PurchaseOrderDraftJpaEntity purchaseOrderDraftJpaEntity = purchaseOrderDraftJpaEntityRepository.
                findPurchaseOrderDraftJpaEntitiesByPoNumber(poNumber).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Purchase order was not found"));

        int lineNumber = purchaseOrderDraftJpaEntity.getOrderLines().size() + 1;

        OrderLineDraftJpaEntity orderLineDraftJpaEntity = new OrderLineDraftJpaEntity(lineNumber, orderLine.getMaterialType(),
                orderLine.getQuantity(), poNumber);

        purchaseOrderDraftJpaEntity.getOrderLines().add(orderLineDraftJpaEntity);
        purchaseOrderDraftJpaEntityRepository.save(purchaseOrderDraftJpaEntity);
    }
}
