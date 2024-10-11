package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.GetOutstandingPOSUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetOutstandingPOSUseCase implements GetOutstandingPOSUseCase {

    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultGetOutstandingPOSUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public List<ShipmentOrder> getOutstandingPOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return ShipmentOrder.getOutStandingPOS(shipmentOrders);
    }
}
