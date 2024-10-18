package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
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
    public List<ShipmentOrder> getFinishedPOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return shipmentOrders.stream().filter(shipmentOrder -> shipmentOrder.getShipmentStatus().ordinal() ==
                ShipmentStatus.LOADED.ordinal()).toList();
    }

    @Override
    public List<ShipmentOrder> getOutstandingIOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return shipmentOrders.stream().filter(shipmentOrder -> shipmentOrder.getShipmentStatus().ordinal() ==
                ShipmentStatus.ARRIVED.ordinal()).toList();
    }

    @Override
    public List<ShipmentOrder> getOutstandingBOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return shipmentOrders.stream().filter(shipmentOrder -> shipmentOrder.getShipmentStatus().ordinal() ==
                ShipmentStatus.INSPECTED.ordinal()).toList();
    }

    @Override
    public List<ShipmentOrder> getToLoadPOS() {
        List<ShipmentOrder> shipmentOrders= loadOrCreateShipmentOrderPort.getShipmentOrders();
        return shipmentOrders.stream().filter(shipmentOrder -> shipmentOrder.getShipmentStatus().ordinal() ==
                ShipmentStatus.BUNKERED.ordinal()).toList();
    }

}
