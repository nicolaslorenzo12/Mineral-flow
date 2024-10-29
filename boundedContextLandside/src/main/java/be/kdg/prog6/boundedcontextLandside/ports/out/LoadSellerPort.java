package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.common.domain.Seller;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadSellerPort {
    Optional<Seller> loadSellerByUUID(UUID uuid);
    Optional<Seller> loadSellerByName(String sellerName);
    List<Seller> loadAllSellers();
}
