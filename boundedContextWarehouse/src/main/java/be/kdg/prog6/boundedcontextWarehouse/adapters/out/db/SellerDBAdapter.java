package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Address;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SellerDBAdapter implements LoadSellerPort {

    private final SellerJpaRepository sellerJpaRepository;

    public SellerDBAdapter(SellerJpaRepository sellerRepository) {
        this.sellerJpaRepository = sellerRepository;
    }

    @Override
    public Optional<Seller> loadSellerBySellerUUID(Seller.CustomerUUID sellerUUID) {
        return sellerJpaRepository.findBySellerUUID(sellerUUID.uuid()).map(this::buildSellerObject);
    }

    private Seller buildSellerObject(SellerJpaEntity sellerJpaEntity) {
        Seller.CustomerUUID customerUUID = new Seller.CustomerUUID(sellerJpaEntity.getSellerUUID());
        String name = sellerJpaEntity.getName();

        // Assuming address is already loaded due to the relationship
        AddressJpaEntitiy addressJpaEntity = sellerJpaEntity.getAddressJpaEntity();
        Address address = new Address(
                addressJpaEntity.getStreet(),
                addressJpaEntity.getHouseNumber(),
                addressJpaEntity.getCity(),
                addressJpaEntity.getCountry(),
                new Address.AddressUUID(addressJpaEntity.getAddressUUID())
        );

        return new Seller(customerUUID, name, address);
    }
}
