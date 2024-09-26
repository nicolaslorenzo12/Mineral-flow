package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;

import java.util.Optional;
import java.util.UUID;

public interface LoadSellerPort {
    Optional<Seller> loadSellerByUUID(UUID uuid);
}
