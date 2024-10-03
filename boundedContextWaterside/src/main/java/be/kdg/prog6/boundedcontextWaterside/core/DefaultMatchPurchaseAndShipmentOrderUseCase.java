package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.MatchPurchaseAndShipmentOrderUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DefaultMatchPurchaseAndShipmentOrderUseCase implements MatchPurchaseAndShipmentOrderUseCase {

    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultMatchPurchaseAndShipmentOrderUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public void matchPurchaseAndShipmentOrder(MatchPurchaseAndShipmentOrderCommand matchPurchaseAndShipmentOrderCommand) {

        String poNumber = matchPurchaseAndShipmentOrderCommand.poNumber();
        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(matchPurchaseAndShipmentOrderCommand.poNumber())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Shipment order was not found"));

        if(!shipmentOrder.getPoNumber().equals(poNumber)){
            throw new CustomException(HttpStatus.CONFLICT, "Purchase order and shipment order do not match");
        }
    }
}
