package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.boundedcontextInvoice.domain.Warehouse;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("invoiceWarehouseDatabaseAdapter")
public class WarehouseDBAdapter implements LoadWarehousePort {

    private final WarehouseJpaEntityRepository warehouseJpaEntityRepository;

    public WarehouseDBAdapter(WarehouseJpaEntityRepository warehouseJpaEntityRepository) {
        this.warehouseJpaEntityRepository = warehouseJpaEntityRepository;
    }

    @Override
    public Optional<Warehouse> findWarehouseByWarehouseNumber(int warehouseNumber){
       Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseJpaEntityRepository.findWarehouseJpaEntityByWarehouseNumber(warehouseNumber);

        return warehouseJpaEntity.map(this::buildWarehouseObject);
    }

    public Warehouse buildWarehouseObject(WarehouseJpaEntity warehouseJpaEntity) {

        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(warehouseJpaEntity.getSellerUUID());

        return new Warehouse(warehouseJpaEntity.getWarehouseNumber(), sellerUUID, warehouseJpaEntity.getMaterialType());
    }
}
