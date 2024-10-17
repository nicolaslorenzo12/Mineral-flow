package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.common.domain.Seller;

public record GetSellerByUUIDCommand(Seller.CustomerUUID sellerUUID) {
}
