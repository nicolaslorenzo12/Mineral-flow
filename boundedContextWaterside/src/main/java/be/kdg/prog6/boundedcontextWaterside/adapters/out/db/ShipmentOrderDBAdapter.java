package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.boundedcontextWaterside.ports.out.UpdateShipmentOrderPort;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ShipmentOrderDBAdapter implements LoadOrCreateShipmentOrderPort, UpdateShipmentOrderPort {

    private final ShipmentOrderJpaEntityRepository shipmentOrderJpaEntityRepository;

    public ShipmentOrderDBAdapter(ShipmentOrderJpaEntityRepository shipmentOrderJpaEntityRepository) {
        this.shipmentOrderJpaEntityRepository = shipmentOrderJpaEntityRepository;
    }


}
