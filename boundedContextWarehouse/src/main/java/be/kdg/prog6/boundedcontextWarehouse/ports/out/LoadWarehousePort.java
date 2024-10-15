package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.util.List;
import java.util.Optional;

public interface LoadWarehousePort {

    Optional<Warehouse> loadWarehouseByWarehouseNumber(int warehouseNumber);
    Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(Seller.CustomerUUID sellerUUID, MaterialType materialType);
    List<Warehouse> loadAllWarehouses();
}
