import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;

import java.util.Optional;
import java.util.UUID;

public class StubLoadOrCreateWarehousePort implements LoadOrCreateWarehousePort {

    private Warehouse warehouse;

    public void setWarehouse(StubWarehouse warehouse) {
        this.warehouse = warehouse;
    }


    public Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(UUID sellerUUID, MaterialType materialType) {

        if(sellerUUID.equals(warehouse.getSellerUUID().uuid()) && materialType.equals(MaterialType.GP)) {

            return Optional.of(warehouse);
        }
        else{
            return Optional.empty();
        }
    }
}