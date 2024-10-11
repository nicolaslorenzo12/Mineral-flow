package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.GetOutstandingOperationsUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGetOutstandingOperationsUseCase implements GetOutstandingOperationsUseCase {

    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultGetOutstandingOperationsUseCase(LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public List<ShipmentOrder> getOutstandingPOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return ShipmentOrder.getOutStandingPOS(shipmentOrders);
    }

    @Override
    public List<ShipmentOrder> getOutstandingIOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return ShipmentOrder.getOutStandingIOS(shipmentOrders);
    }
}
