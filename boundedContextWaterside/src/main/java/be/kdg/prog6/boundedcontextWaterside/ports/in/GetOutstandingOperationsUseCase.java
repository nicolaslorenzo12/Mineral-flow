package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;

public interface GetOutstandingOperationsUseCase {

    List<ShipmentOrder> getFinishedPOS();
    List<ShipmentOrder> getOutstandingIOS();
    List<ShipmentOrder> getOutstandingBOS();
    List<ShipmentOrder> getToLoadPOS();
}
