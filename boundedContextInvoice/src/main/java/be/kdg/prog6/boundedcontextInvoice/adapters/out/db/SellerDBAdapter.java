package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("invoiceSellerDatabaseAdapter")
public class SellerDBAdapter implements LoadSellerPort {

    private final SellerJpaEntityRepository sellerJpaEntityRepository;

    public SellerDBAdapter(SellerJpaEntityRepository sellerJpaEntityRepository) {
        this.sellerJpaEntityRepository = sellerJpaEntityRepository;
    }

    @Override
    public Optional<Seller> loadSellerBySellerUUID(Seller.CustomerUUID sellerUUID) {
        Optional<SellerJpaEntity> sellerJpaEntity = sellerJpaEntityRepository.findBySellerUUID(sellerUUID.uuid());
        return sellerJpaEntity.map(this::buildSellerObject);
    }

    private Seller buildSellerObject(SellerJpaEntity sellerJpaEntity){

        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(sellerJpaEntity.getSellerUUID());
        return new Seller(sellerUUID, sellerJpaEntity.getName());

    }
}
