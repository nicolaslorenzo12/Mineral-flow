package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;

public interface GetOutstandingOperationsUseCase {

    public List<ShipmentOrder> getOutstandingPOS();
    List<ShipmentOrder> getOutstandingIOS();
}
