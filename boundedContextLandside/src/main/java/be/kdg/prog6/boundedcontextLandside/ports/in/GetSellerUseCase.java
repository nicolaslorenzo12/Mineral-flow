package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.common.domain.Seller;

import java.util.List;

public interface GetSellerUseCase {

    Seller getSellerBySellerUUID(GetSellerByUUIDCommand getSellerCommand);
    Seller getSellerByName(GetSellerByNameCommand getSellerCommand);
    List<Seller> getAllSellers();
}
