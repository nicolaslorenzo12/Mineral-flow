package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;

import java.util.List;

public interface GetOutstandingPOSUseCase {

    public List<ShipmentOrder> getOutstandingPOS();
}
