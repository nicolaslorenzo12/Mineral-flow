package be.kdg.prog6.boundedcontextInvoice.ports.out;

import be.kdg.prog6.common.domain.Seller;

import java.util.Optional;

public interface LoadSellerPort {

    Optional<Seller> loadSellerBySellerUUID(Seller.CustomerUUID sellerUUID);
}
