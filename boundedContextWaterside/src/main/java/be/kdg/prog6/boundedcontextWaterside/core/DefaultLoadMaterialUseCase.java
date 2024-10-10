package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadMaterialCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadMaterialUseCase implements LoadMaterialUseCase {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;
    private final LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultLoadMaterialUseCase(List<UpdateShipmentOrderPort> updateShipmentOrderPorts, LoadOrCreateShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public void loadMaterial(LoadMaterialCommand loadMaterialCommand) {

        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadOrCreateShipmentOrder(loadMaterialCommand.shipmentOrderUUID());
        updateShipmentOrderPorts.forEach(updateShipmentOrderPort -> {updateShipmentOrderPort.loadOrLoadedMaterial(shipmentOrder);});
    }

}
