package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.common.domain.Seller;

public interface GetSellerUseCase {

    Seller getSellerBySellerUUID(GetSellerCommand getSellerCommand);
}
