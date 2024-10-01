package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("landsideDatabaseAdapter")
public class WarehouseDBAdapter implements LoadOrCreateWarehousePort, UpdateWarehousePort {
    private final WarehouseRepository warehouseRepository;

    public WarehouseDBAdapter(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse loadWarehouseBySellerUUIDAndMaterialType(UUID sellerUuid, MaterialType materialType) {
        final WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.
                findBySellerUUIDAndMaterialType(sellerUuid, materialType).orElseThrow();

        return new Warehouse(warehouseJpaEntity.getWareHouseNumber(), new Seller.CustomerUUID(warehouseJpaEntity.getSellerUUID()),
                warehouseJpaEntity.getUtilizationCapacity(), materialType);
    }
    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseRepository.save(new WarehouseJpaEntity(warehouse.getWareHouseNumber(), warehouse.getSellerUUID().uuid(), warehouse.getMaterialType(),
                warehouse.getCurrentStockStorage()));
    }
}
