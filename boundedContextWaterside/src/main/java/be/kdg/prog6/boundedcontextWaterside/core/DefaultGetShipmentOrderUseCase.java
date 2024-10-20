package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.GetShipmentOrderCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.GetShipmentOrderUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadShipmentOrderPort;
import org.springframework.stereotype.Service;

@Service
public class DefaultGetShipmentOrderUseCase implements GetShipmentOrderUseCase {

    private final LoadShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultGetShipmentOrderUseCase(LoadShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public ShipmentOrder getShipmentOrder(GetShipmentOrderCommand getShipmentOrderCommand) {
        return loadOrCreateShipmentOrderPort.loadShipmentOrder(new ShipmentOrder.ShipmentOrderUUID(getShipmentOrderCommand.shipmentOrderUUID()));
    }
}
