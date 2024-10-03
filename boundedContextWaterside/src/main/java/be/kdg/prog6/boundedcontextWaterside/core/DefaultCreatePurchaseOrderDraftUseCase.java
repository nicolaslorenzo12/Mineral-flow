package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.ports.in.CreatePurchaseOrderDraftCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreatePurchaseOrderDraftUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.CreatePurchaseOrderDraftPort;
import org.springframework.stereotype.Service;

@Service
public class DefaultCreatePurchaseOrderDraftUseCase implements CreatePurchaseOrderDraftUseCase {

    private final CreatePurchaseOrderDraftPort createPurchaseOrderDraftPort;

    public DefaultCreatePurchaseOrderDraftUseCase(CreatePurchaseOrderDraftPort createPurchaseOrderDraftPort) {
        this.createPurchaseOrderDraftPort = createPurchaseOrderDraftPort;
    }

    @Override
    public void createPurchaseOrder(CreatePurchaseOrderDraftCommand createPurchaseOrderDraftCommand) {

        createPurchaseOrderDraftPort.createPurchaseOrderDraft(createPurchaseOrderDraftCommand.sellerUUID().uuid(),
                createPurchaseOrderDraftCommand.buyerCustomerUUID().uuid(), createPurchaseOrderDraftCommand.vesselNumber(),
                createPurchaseOrderDraftCommand.purchaseOrderDate());
    }
}
