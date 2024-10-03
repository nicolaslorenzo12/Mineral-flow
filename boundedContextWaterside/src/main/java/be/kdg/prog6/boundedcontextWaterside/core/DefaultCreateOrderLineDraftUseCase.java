package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.OrderLine;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreateOrderLineDraftCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreateOrderLineDraftUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreatePurchaseOrderDraftPort;
import org.springframework.stereotype.Service;

@Service
public class DefaultCreateOrderLineDraftUseCase implements CreateOrderLineDraftUseCase {

    private final LoadOrCreatePurchaseOrderDraftPort loadOrCreatePurchaseOrderDraftPort;

    public DefaultCreateOrderLineDraftUseCase(LoadOrCreatePurchaseOrderDraftPort loadOrCreatePurchaseOrderDraftPort) {
        this.loadOrCreatePurchaseOrderDraftPort = loadOrCreatePurchaseOrderDraftPort;
    }

    @Override
    public void createOrderLineDraftUseCase(CreateOrderLineDraftCommand createOrderLineDraftCommand) {

        OrderLine orderLine = new OrderLine(createOrderLineDraftCommand.materialType(), createOrderLineDraftCommand.quantity());
        loadOrCreatePurchaseOrderDraftPort.createOrderLineDraft(createOrderLineDraftCommand.poNumber(),orderLine);
    }
}
