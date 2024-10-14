package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.OrderLine;
import be.kdg.prog6.common.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadPurchaseOrderPort;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Seller;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PurchaseOrderDBAdapter implements LoadPurchaseOrderPort {

    private final PurchaseOrderJpaEntityRepository purchaseOrderJpaEntityRepository;

    public PurchaseOrderDBAdapter(PurchaseOrderJpaEntityRepository purchaseOrderJpaEntityRepository) {
        this.purchaseOrderJpaEntityRepository = purchaseOrderJpaEntityRepository;
    }

    @Override
    @Transactional
    public PurchaseOrder loadPurchaseOrderByShipmentOrderUUID(UUID shipmentOrderUUID) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaEntityRepository.findByShipmentOrderUUID(shipmentOrderUUID);
        return buildPurchaseOrderObject(purchaseOrderJpaEntity);
    }

    private PurchaseOrder buildPurchaseOrderObject(PurchaseOrderJpaEntity jpaEntity) {

        return new PurchaseOrder(
                jpaEntity.getPoNumber(),
                jpaEntity.getShipmentOrderUUID(),
                new Seller.CustomerUUID(jpaEntity.getSellerUUID()),
                new Buyer.CustomerUUID(jpaEntity.getBuyerUuid()),
                jpaEntity.getVesselNumber(),
                jpaEntity.getPurchaseOrderDate(),
                jpaEntity.getOrderLines().stream()
                        .map(this::mapOrderLine)
                        .collect(Collectors.toList())
        );
    }

    private OrderLine mapOrderLine(OrderLineJpaEntity orderLineJpaEntity) {

        return new OrderLine(
                new OrderLine.OrderLineUUID(orderLineJpaEntity.getOrderLineUUID()),
                orderLineJpaEntity.getLineNumber(),
                orderLineJpaEntity.getMaterialType(),
                orderLineJpaEntity.getQuantity()
        );
    }
}
