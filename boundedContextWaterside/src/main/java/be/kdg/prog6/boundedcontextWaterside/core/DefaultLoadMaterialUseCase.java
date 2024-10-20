package be.kdg.prog6.boundedcontextWaterside.core;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadMaterialCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.LoadMaterialUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadMaterialUseCase implements LoadMaterialUseCase {

    private final List<UpdateShipmentOrderPort> updateShipmentOrderPorts;
    private final LoadShipmentOrderPort loadOrCreateShipmentOrderPort;

    public DefaultLoadMaterialUseCase(List<UpdateShipmentOrderPort> updateShipmentOrderPorts, LoadShipmentOrderPort loadOrCreateShipmentOrderPort) {
        this.updateShipmentOrderPorts = updateShipmentOrderPorts;
        this.loadOrCreateShipmentOrderPort = loadOrCreateShipmentOrderPort;
    }

    @Override
    public void loadMaterial(LoadMaterialCommand loadMaterialCommand) {

        ShipmentOrder shipmentOrder = loadOrCreateShipmentOrderPort.loadShipmentOrderByVesselNumber(loadMaterialCommand.vesselNumber());
        shipmentOrder.checkIfShipmentOrderHasAlreadyHadThisStatus(ShipmentStatus.LOADED);
        updateShipmentOrderPorts.forEach(updateShipmentOrderPort -> {updateShipmentOrderPort.updateShipmentOrder(shipmentOrder, true);});
    }

}
