package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SellerDBAdapter implements LoadSellerPort {

    private final SellerRepository sellerRepository;

    public SellerDBAdapter(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    @Override
    public Optional<Seller> loadSellerByUUID(UUID uuid) {
        Optional<SellerJpaEntity> sellerJpaEntity = sellerRepository.findBySellerUUID(uuid);

        if(sellerJpaEntity.isEmpty()){
            return Optional.empty();
        }

        Seller seller = buildSellerObject(sellerJpaEntity);
        return Optional.of(seller);
    }

    private Seller buildSellerObject(Optional<SellerJpaEntity> sellerJpaEntity){
        return new Seller(new Seller.CustomerUUID(sellerJpaEntity.get().getSellerUUID()), sellerJpaEntity.get().getName());
    }
}
