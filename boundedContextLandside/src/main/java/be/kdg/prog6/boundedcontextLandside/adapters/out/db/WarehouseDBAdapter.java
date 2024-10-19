package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component("landsideDatabaseAdapter")
public class WarehouseDBAdapter implements LoadOrCreateWarehousePort, UpdateWarehousePort {
    private final WarehouseRepository warehouseRepository;

    public WarehouseDBAdapter(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(UUID sellerUuid, MaterialType materialType) {
        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.
                findBySellerUUIDAndMaterialType(sellerUuid, materialType);

        return warehouseJpaEntity.map(this::buildWarehouseObject);
    }
    @Override
    public void updateWarehouse(Warehouse warehouse, UpdateWarehouseAction updateWarehouseAction, UUID appointmentUUID, LocalDateTime dateTime) {

        if(updateWarehouseAction.equals(UpdateWarehouseAction.CHANGE_WAREHOUSE_STOCK)) {
            warehouseRepository.save(new WarehouseJpaEntity(warehouse.getWareHouseNumber(), warehouse.getSellerUUID().uuid(), warehouse.getMaterialType(),
                    warehouse.getCurrentStockStorage()));
        }
    }

    private Warehouse buildWarehouseObject(WarehouseJpaEntity warehouseJpaEntity) {
        return new Warehouse(
                warehouseJpaEntity.getWareHouseNumber(),
                new Seller.CustomerUUID(warehouseJpaEntity.getSellerUUID()),
                warehouseJpaEntity.getUtilizationCapacity(),
                warehouseJpaEntity.getMaterialType()
        );
    }
}
