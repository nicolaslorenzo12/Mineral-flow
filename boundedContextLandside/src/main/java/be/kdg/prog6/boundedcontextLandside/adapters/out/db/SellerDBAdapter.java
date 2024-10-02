package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component("landSideSellerDatabaseAdapter")
public class SellerDBAdapter implements LoadSellerPort {

    private final SellerRepository sellerRepository;

    public SellerDBAdapter(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    @Override
    public Optional<Seller> loadSellerByUUID(UUID uuid) {
        Optional<SellerJpaEntity> sellerJpaEntity = sellerRepository.findBySellerUUID(uuid);

        return sellerJpaEntity.map(this::buildSellerObject);
    }

    private Seller buildSellerObject(SellerJpaEntity sellerJpaEntity){
        return new Seller(new Seller.CustomerUUID(sellerJpaEntity.getSellerUUID()), sellerJpaEntity.getName());
    }
}
