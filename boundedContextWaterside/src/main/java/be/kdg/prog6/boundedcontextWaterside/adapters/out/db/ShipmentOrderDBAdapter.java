package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.domain.OrderLine;
import be.kdg.prog6.boundedcontextWaterside.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import be.kdg.prog6.boundedcontextWaterside.ports.out.LoadOrCreateShipmentOrderPort;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ShipmentOrderDBAdapter implements LoadOrCreateShipmentOrderPort {

    private final ShipmentOrderJpaEntityRepository shipmentOrderJpaEntityRepository;

    public ShipmentOrderDBAdapter(ShipmentOrderJpaEntityRepository shipmentOrderJpaEntityRepository) {
        this.shipmentOrderJpaEntityRepository = shipmentOrderJpaEntityRepository;
    }

    private ShipmentOrder buildShipmentOrderObject(ShipmentOrderJpaEntity shipmentOrderJpaEntity){

        return new ShipmentOrder(shipmentOrderJpaEntity.getPoNumber(), shipmentOrderJpaEntity.getEstimatedArrivalDate(),
                shipmentOrderJpaEntity.getEstimatedDepartureDate(), shipmentOrderJpaEntity.getActualArrivalDate(),
                shipmentOrderJpaEntity.getActualDepartureDate(), shipmentOrderJpaEntity.getShipmentStatus(),
                new ShipmentOrder.ShipmentOrderUUID(shipmentOrderJpaEntity.getShipmentOrderUUID()));
    }


    @Override
    public Optional<ShipmentOrder> loadOrCreateShipmentOrder(String poNumber) {

        Optional<ShipmentOrderJpaEntity> shipmentOrderJpaEntity = shipmentOrderJpaEntityRepository.findShipmentOrderJpaEntityByPoNumber(poNumber);

        return shipmentOrderJpaEntity.map(this::buildShipmentOrderObject);
    }

    private PurchaseOrder buildPurchaseOrderObject(PurchaseOrderJpaEntity purchaseOrderJpaEntity){

        return new PurchaseOrder(purchaseOrderJpaEntity.getPoNumber(), new Seller.CustomerUUID(purchaseOrderJpaEntity.getSellerUUID()),
                new Buyer.CustomerUUID(purchaseOrderJpaEntity.getBuyerUuid()), purchaseOrderJpaEntity.getVesselNumber(),
                purchaseOrderJpaEntity.getPurchaseOrderDate(), buildOrderLineObjects(purchaseOrderJpaEntity.getOrderLines()));
    }

    private List<OrderLine> buildOrderLineObjects(List<OrderLineJpaEntity> orderLineJpaEntities){

        return orderLineJpaEntities.stream()
                .map(jpaEntity -> new OrderLine(
                        jpaEntity.getLineNumber(),
                        jpaEntity.getMaterialType(),
                        jpaEntity.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PurchaseOrder> loadPurchaseOrder(String poNumber) {

        Optional<ShipmentOrderJpaEntity> shipmentOrderJpaEntity = shipmentOrderJpaEntityRepository.findShipmentOrderJpaEntityByPoNumber(poNumber);

        if(shipmentOrderJpaEntity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(buildPurchaseOrderObject(shipmentOrderJpaEntity.get().getPurchaseOrder()));
    }
}
