package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.Seller;

public interface GetSellerUseCase {

    Seller getSeller(GetSellerCommand getSellerCommand);
}
