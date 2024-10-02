package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.util.Optional;
import java.util.UUID;

public interface LoadOrCreateWarehousePort {
    Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(UUID sellerUuid, MaterialType materialType);
}
