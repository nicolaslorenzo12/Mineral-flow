package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.Seller;

public record GetSellerCommand(Seller.CustomerUUID sellerUUID){
}
